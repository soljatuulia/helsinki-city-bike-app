package net.virkkunen.backend.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import net.virkkunen.backend.entities.Journey;
import net.virkkunen.backend.repositories.JourneyRepository;

@Service
public class JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Transactional
    @PostConstruct
    public void init() throws IOException {
      try {
        saveJourneysFromCsv("C://helsinki-city-bike-app//helsinki-city-bike-app//backend//src//main//resources//journeys.csv");
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    @Transactional
    public void saveJourneysFromCsv(String csvFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath), "UTF-8"));
        String line;
        List<Journey> journeys = new ArrayList<>();
        
        boolean firstLine = true; 
        while ((line = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] fields = line.split(",");
            try {
              LocalDateTime departureTime = LocalDateTime.parse(fields[0], DateTimeFormatter.ISO_DATE_TIME);
              LocalDateTime returnTime = LocalDateTime.parse(fields[1], DateTimeFormatter.ISO_DATE_TIME);
              Integer departureStationId = Integer.parseInt(fields[2]);
              String departureStationName = fields[3];
              Integer returnStationId = Integer.parseInt(fields[4]);
              String returnStationName = fields[5];
              Integer distance = Integer.parseInt(fields[6]);
              Integer duration = Integer.parseInt(fields[7]);

              if (departureTime.isBefore(returnTime) & departureStationId > 0 & distance >= 10 & duration >= 10) {            
                Journey journey = new Journey(departureTime, returnTime, departureStationId,
                departureStationName, returnStationId, returnStationName, distance,
                duration);

                journeys.add(journey);
              }
            } catch (NumberFormatException ex) {
              ex.printStackTrace();
          }
        }
        br.close();
        journeyRepository.saveAll(journeys);
    }

}
