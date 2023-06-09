package net.virkkunen.backend.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.virkkunen.backend.entities.Journey;
import net.virkkunen.backend.repositories.JourneyRepository;

@Service
public class JourneyService {

  private JourneyRepository journeyRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public JourneyService(JourneyRepository journeyRepository) {
    this.journeyRepository = journeyRepository;
  }

  public void importJourneyData() throws IOException {
    // update correct file paths to journey data (May, June and July) below
    String csvFilePathMay = "";
    String csvFilePathJune = "";
    String csvFilePathJuly = "";
    
    try {
      System.out.println("We are in JourneyService init()");
      createJourneyTable();
      saveJourneysFromCsv(csvFilePathMay);
      saveJourneysFromCsv(csvFilePathJune);
      saveJourneysFromCsv(csvFilePathJuly);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void createJourneyTable() {
    String createTableQuery = "CREATE TABLE IF NOT EXISTS journey (" +
            "journey_id INT NOT NULL AUTO_INCREMENT, " +
            "departure_time DATETIME NULL, " +
            "return_time DATETIME NULL, " +
            "departure_station_id INT NULL, " +
            "departure_station_name VARCHAR(45) NULL, " +
            "return_station_id INT NULL, " +
            "return_station_name VARCHAR(45) NULL, " +
            "distance INT NULL, " +
            "duration INT NULL, " +
            "PRIMARY KEY (journey_id))";
  
    jdbcTemplate.execute(createTableQuery);
  }

  @Transactional
  public void saveJourneysFromCsv(String csvFilePath) throws IOException {
    List<Journey> journeys = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath), "UTF-8"))) {
      String line;
      boolean firstLine = true;
      while ((line = br.readLine()) != null) {
        if (firstLine) {
          firstLine = false;
          continue;
        }

        String[] fields = line.split(",");
        parseAndAddValidJourney(fields, journeys);
      }
    }

    journeyRepository.saveAll(journeys);
  }

  public void parseAndAddValidJourney(String[] fields, List<Journey> journeys) {
    LocalDateTime departureTime;
    LocalDateTime returnTime;
    Integer departureStationId;
    String departureStationName;
    Integer returnStationId;
    String returnStationName;
    Integer distance;
    Integer duration;
  
    try {
      departureTime = LocalDateTime.parse(fields[0], DateTimeFormatter.ISO_DATE_TIME);
      returnTime = LocalDateTime.parse(fields[1], DateTimeFormatter.ISO_DATE_TIME);
      departureStationId = Integer.parseInt(fields[2]);
      departureStationName = fields[3];
      returnStationId = Integer.parseInt(fields[4]);
      returnStationName = fields[5];
      distance = Integer.parseInt(fields[6]);
      duration = Integer.parseInt(fields[7]);
    } catch (NumberFormatException | DateTimeParseException ex) {
      ex.printStackTrace();
      return;
    }
  
    if (departureTime.isBefore(returnTime) &&
        departureStationId > 0 &&
        returnStationId > 0 &&
        distance >= 10 &&
        duration >= 10) {

      Journey journey = new Journey(departureTime, returnTime, departureStationId,
                  departureStationName, returnStationId, returnStationName, distance,
                  duration);
  
      journeys.add(journey);
    }
  }
  
}
