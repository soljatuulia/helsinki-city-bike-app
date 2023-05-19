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
        String csvFilePath = "C://helsinki-city-bike-app//helsinki-city-bike-app//backend//src//main//resources//journeylist.csv";
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
                try {
                    parseAndAddValidJourney(fields, journeys);
                } catch (NumberFormatException | DateTimeParseException ex) {
                    ex.printStackTrace();
                    throw ex;
                }
            }
        }

        journeyRepository.saveAll(journeys);
    }

    private void parseAndAddValidJourney(String[] fields, List<Journey> journeys) {
        LocalDateTime departureTime = LocalDateTime.parse(fields[0], DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime returnTime = LocalDateTime.parse(fields[1], DateTimeFormatter.ISO_DATE_TIME);
        Integer departureStationId = Integer.parseInt(fields[2]);
        String departureStationName = fields[3].replace("\"", "").trim();
        Integer returnStationId = Integer.parseInt(fields[4]);
        String returnStationName = fields[5].replace("\"", "").trim();
        Integer distance = Integer.parseInt(fields[6]);
        Integer duration = Integer.parseInt(fields[7]);

        if (returnTime.isBefore(departureTime)) {
            throw new IllegalArgumentException("Departure time must be earlier than return time");
        } else if (departureStationId < 0 || returnStationId < 0) {
            throw new IllegalArgumentException("Station id can not be negative");
        } else if (distance < 10) {
            throw new IllegalArgumentException("Distance must be over 10 meters");
        } else if (duration < 10) {
            throw new IllegalArgumentException("Duration must be over 10 seconds");
        }

        Journey journey = new Journey(departureTime, returnTime, departureStationId,
                departureStationName, returnStationId, returnStationName, distance,
                duration);

        journeys.add(journey);
    }
}
