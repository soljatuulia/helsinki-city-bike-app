package net.virkkunen.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.virkkunen.backend.entities.Station;
import net.virkkunen.backend.entities.StationDetails;
import net.virkkunen.backend.repositories.JourneyRepository;
import net.virkkunen.backend.repositories.StationRepository;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class StationController {

  @Autowired
  StationRepository stationRepo;

  @Autowired
  JourneyRepository journeyRepo;

  @GetMapping(value = "/stations", produces = "application/json")
  public Page<Station> listStations(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
    @RequestParam(value = "size", defaultValue = "10") int pageSize ) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return stationRepo.findAll(pageable);
  }

  @GetMapping(value = "/stations/{id}")
  public StationDetails getStationDetails(@PathVariable int id) {
    Station station = stationRepo.findById(id).orElse(null);
    
    if (station==null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found");
    }

    StationDetails stationDetails = new StationDetails(station.getName(), station.getAddress(), 
          journeyRepo.totalDepartsPerStation(station.getJourneyStationId()), 
          journeyRepo.totalReturnsPerStation(station.getJourneyStationId()));
          System.out.println("Station name is " + station.getName());
    
    return stationDetails;
  }
    
}
