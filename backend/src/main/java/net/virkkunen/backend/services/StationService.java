package net.virkkunen.backend.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import net.virkkunen.backend.entities.Station;
import net.virkkunen.backend.repositories.StationRepository;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    @Transactional
    @PostConstruct
    public void init() throws IOException {
      try {
        saveStationsFromCsv("C://helsinki-city-bike-app//helsinki-city-bike-app//backend//src//main//resources//stations.csv");
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    @Transactional
    public void saveStationsFromCsv(String csvFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath), "UTF-8"));
        String line;
        List<Station> stations = new ArrayList<>();

        boolean firstLine = true; 
        while ((line = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] fields = line.split(",");
            try {
              Integer journeyStationId = Integer.parseInt(fields[1]);
              String name = fields[2];
              String nameSwedish = fields[3];
              String address = fields[5];
              String addressSwedish = fields[6];
              String city = fields[7];
              String citySwedish = fields[8];
              String operator = fields[9];
              Integer capacity = Integer.parseInt(fields[10]);
              Double x = Double.parseDouble(fields[11]);
              Double y = Double.parseDouble(fields[12]);
            
              Station station = new Station(journeyStationId, name, nameSwedish, address, addressSwedish, city,
                      citySwedish, operator, capacity, x, y);
            
              stations.add(station);
            } catch (NumberFormatException ex) {
              ex.printStackTrace();
            }

        br.close();
        stationRepository.saveAll(stations);
    }
  }
}