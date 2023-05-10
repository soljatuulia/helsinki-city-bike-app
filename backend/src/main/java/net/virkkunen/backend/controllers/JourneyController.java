package net.virkkunen.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.virkkunen.backend.entities.Journey;
import net.virkkunen.backend.repositories.JourneyRepository;

@RestController
@RequestMapping("/api")
public class JourneyController {

  @Autowired
  JourneyRepository journeyRepo;

  @GetMapping(value="/journeys")
  public Page<Journey> getJourneys (Pageable pageable) {
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/departureasc")
  public Page<Journey> getJourneysByDepartureAsc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("departureStationName").ascending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/departuredesc")
  public Page<Journey> getJourneysByDepartureDesc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("departureStationName").descending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/returnasc")
  public Page<Journey> getJourneysByReturnAsc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("returnStationName").ascending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/returndesc")
  public Page<Journey> getJourneysByReturnDesc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("returnStationName").descending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/durationasc")
  public Page<Journey> getJourneysByDurationAsc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("durationInMin").ascending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/durationdesc")
  public Page<Journey> getJourneysByDurationDesc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("durationInMin").descending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/distanceasc")
  public Page<Journey> getJourneysByDistanceAsc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("distanceInKm").ascending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping(value="journeys/distancedesc")
  public Page<Journey> getJourneysByDistanceDesc() {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("distanceInKm").descending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable);
    return journeys;
  }

  @GetMapping("/journeys/{id}")
  Journey get(@PathVariable int id) {
      Journey journey = journeyRepo.findById(id).orElse(null);
      if (journey==null) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journey not found");
      }
      return journey;
  }
  
}