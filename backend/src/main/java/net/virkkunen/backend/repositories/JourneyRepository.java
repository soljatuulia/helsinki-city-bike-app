package net.virkkunen.backend.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.virkkunen.backend.entities.Journey;

public interface JourneyRepository extends JpaRepository<Journey,Integer> {

  @Query("SELECT j.journeyId, j.departureTime, j.returnTime, j.departureStationName, j.returnStationName, " +
        "j.distance / 1000.0 AS distanceInKm, j.duration / 60.0 AS durationInMin " + 
        "FROM Journey j " + 
        "WHERE DAY(j.departureTime) = :day " +
        "AND MONTH(j.departureTime) = :month " + 
        "AND (j.departureStationName LIKE %:filter% OR j.returnStationName LIKE %:filter%)")
  Page<Journey> listJourneys(Pageable pageable, @Param("filter") String filter, @Param("day") int day, @Param("month") int month);
  
  @Query("SELECT COUNT(j) FROM Journey j WHERE j.departureStationId = :journeyStationId")
  Integer totalDepartsPerStation(@Param("journeyStationId") int journeyStationId);

  @Query("SELECT COUNT(j) FROM Journey j WHERE j.returnStationId = :journeyStationId")
  Integer totalReturnsPerStation(@Param("journeyStationId") int journeyStationId);

  @Query("SELECT AVG(j.distance) / 1000.0 AS averageDepartureDistanceInKm FROM Journey j WHERE j.departureStationId = :journeyStationId")
  Double averageDistanceOfDepartingJourney(@Param("journeyStationId") int journeyStationId);

  @Query("SELECT AVG(j.distance) / 1000.0 AS averageReturnDistanceInKm FROM Journey j WHERE j.returnStationId = :journeyStationId")
  Double averageDistanceOfReturningJourney(@Param("journeyStationId") int journeyStationId);

  Optional<Journey> findByDepartureTimeAndReturnTimeAndDepartureStationIdAndReturnStationId(LocalDateTime departureTime,
      LocalDateTime returnTime, Integer departureStationId, Integer returnStationId);

}
