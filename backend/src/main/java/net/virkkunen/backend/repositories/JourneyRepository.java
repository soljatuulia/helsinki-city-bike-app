package net.virkkunen.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.virkkunen.backend.entities.Journey;

public interface JourneyRepository extends JpaRepository<Journey,Integer> {

  @Query("SELECT j.departureStationName, j.returnStationName, j.distance / 1000.0 AS distanceInKm, j.duration / 60.0 AS durationInMin FROM Journey j")
  Page<Journey> listJourneys(Pageable pageable);

}
