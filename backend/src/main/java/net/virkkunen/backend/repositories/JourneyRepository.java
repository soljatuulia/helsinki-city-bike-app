package net.virkkunen.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.virkkunen.backend.entities.Journey;

public interface JourneyRepository extends JpaRepository<Journey,Integer> {

  @Query("SELECT j.departureStationName, j.returnStationName, j.distance / 1000.0 AS distanceInKm, j.duration / 60.0 AS durationInMin FROM Journey j")
  Page<Journey> listJourneys(Pageable pageable, @Param("sorter") String sorter);

  @Query("SELECT j.departureStationName, j.returnStationName, j.distance / 1000.0 AS distanceInKm, j.duration / 60.0 AS durationInMin FROM Journey j WHERE j.departureStationName LIKE %:filter% OR j.returnStationName LIKE %:filter%")
  Page<Journey> findFiltered(Pageable pageable, @Param("filter") String filter);

  @Query("SELECT COUNT(j) FROM Journey j WHERE j.departureStationId = :journeyStationId")
  Integer totalDepartsPerStation(@Param("journeyStationId") int journeyStationId);

  @Query("SELECT COUNT(j) FROM Journey j WHERE j.returnStationId = :journeyStationId")
  Integer totalReturnsPerStation(@Param("journeyStationId") int journeyStationId);

}
