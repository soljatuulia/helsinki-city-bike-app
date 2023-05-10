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
import org.springframework.web.bind.annotation.RequestParam;
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
  public Page<Journey> getJourneys (Pageable pageable, @RequestParam(defaultValue="") String filter) {
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/departureasc")
  public Page<Journey> getJourneysByDepartureAsc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("departureStationName").ascending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/departuredesc")
  public Page<Journey> getJourneysByDepartureDesc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("departureStationName").descending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/returnasc")
  public Page<Journey> getJourneysByReturnAsc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("returnStationName").ascending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/returndesc")
  public Page<Journey> getJourneysByReturnDesc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("returnStationName").descending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/durationasc")
  public Page<Journey> getJourneysByDurationAsc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("durationInMin").ascending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/durationdesc")
  public Page<Journey> getJourneysByDurationDesc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("durationInMin").descending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/distanceasc")
  public Page<Journey> getJourneysByDistanceAsc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("distanceInKm").ascending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value="journeys/distancedesc")
  public Page<Journey> getJourneysByDistanceDesc(@RequestParam(defaultValue="") String filter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by("distanceInKm").descending());
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
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