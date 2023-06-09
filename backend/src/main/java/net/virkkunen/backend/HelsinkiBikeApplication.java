package net.virkkunen.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.virkkunen.backend.services.JourneyService;
import net.virkkunen.backend.services.StationService;

@SpringBootApplication
public class HelsinkiBikeApplication implements ApplicationRunner {

  @Autowired
  StationService stationService;

  @Autowired
  JourneyService journeyService;

	public static void main(String[] args) {

		SpringApplication.run(HelsinkiBikeApplication.class, args);
    
    System.out.println("Cha cha cha cha cha cha cha!");
	}

  @Override
  public void run(ApplicationArguments args) throws Exception {
      stationService.importStationData();
      journeyService.importJourneyData();
  }

}
