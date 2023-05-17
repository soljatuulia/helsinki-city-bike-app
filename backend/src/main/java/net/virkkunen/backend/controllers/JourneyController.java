package net.virkkunen.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.virkkunen.backend.entities.Journey;
import net.virkkunen.backend.repositories.JourneyRepository;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class JourneyController {

  @Autowired
  JourneyRepository journeyRepo;

  @GetMapping(value = "/journeys", produces = "application/json")
  public Page<Journey> getJourneys (@RequestParam(value = "page", defaultValue = "0") int pageNumber,
    @RequestParam(value = "size", defaultValue = "20") int pageSize, 
    @RequestParam(defaultValue="") String filter) {
    
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Journey> journeys = journeyRepo.findFiltered(pageable, "%" + filter + "%");
    return journeys;
  }

  @GetMapping(value = "journeys/sort")
  public Page<Journey> sortJourneys(@RequestParam(defaultValue = "") String sorter) {
    Pageable pageable = PageRequest.of(10, 10, Sort.by(sorter).descending());
    Page<Journey> journeys = journeyRepo.listJourneys(pageable, sorter);
    return journeys;
  }

  @GetMapping(value = "/journeys/{id}")
  Journey get(@PathVariable int id) {
      Journey journey = journeyRepo.findById(id).orElse(null);
      if (journey==null) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journey not found");
      }
      return journey;
  }
  
}