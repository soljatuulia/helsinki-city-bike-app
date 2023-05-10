package net.virkkunen.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
/*
  @GetMapping("/journeys")
  public List<Journey> getAll() {
    return jrepo.findAll();
  }
*/

  @GetMapping(value="/journeys")
  public Page<Journey> getTrips (Pageable pageable) {
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