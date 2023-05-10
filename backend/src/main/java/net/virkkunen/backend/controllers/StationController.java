package net.virkkunen.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.virkkunen.backend.entities.Station;
import net.virkkunen.backend.repositories.StationRepository;

@RestController
@RequestMapping("/api")
public class StationController {

  @Autowired
  StationRepository stationRepo;

  @GetMapping("/stations")
  public Page<Station> listStations(Pageable pageable) {
    return stationRepo.findAll(pageable);
  }
  
}
