package net.virkkunen.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.virkkunen.backend.entities.Station;
import net.virkkunen.backend.repositories.StationRepository;

@RestController
@RequestMapping("stations")
public class StationController {

  @Autowired
  StationRepository srepo;

  @GetMapping
  public List<Station> getAll() {
    return srepo.findAll();
  }
  
}
