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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import net.virkkunen.backend.entities.Journey;
import net.virkkunen.backend.repositories.JourneyRepository;

@Service
public class JourneyService {

    private JourneyRepository journeyRepository;

    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        String csvFilePath = "C://helsinki-city-bike-app//helsinki-city-bike-app//backend//src//main//resources//journeys0521.csv";
        try {
            System.out.println("We are in JourneyService init()");
            saveJourneysFromCsv(csvFilePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
