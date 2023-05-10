package net.virkkunen.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.virkkunen.backend.entities.Journey;
import net.virkkunen.backend.repositories.JourneyRepository;

@RestController
@RequestMapping("journeys")
public class JourneyController {

  @Autowired
  JourneyRepository jrepo;

  @GetMapping
  public List<Journey> getAll() {
    return jrepo.findAll();
  }
  
}