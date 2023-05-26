package net.virkkunen.backend.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import net.virkkunen.backend.entities.Station;
import net.virkkunen.backend.repositories.StationRepository;

@Service
public class StationService {

  @Autowired
  private StationRepository stationRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public StationService(StationRepository stationRepository) {
    this.stationRepository = stationRepository;
  }

  @PostConstruct
  public void init() throws IOException {
    // update correct file path to station data below
    String csvFilePathStations = "";

    try {      
      createStationTable();
      saveStationsFromCsv(csvFilePathStations);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void createStationTable() {
    String createTableQuery = "CREATE TABLE IF NOT EXISTS station (" +
            "station_id INT NOT NULL AUTO_INCREMENT, " +
            "journey_station_id INT NULL, " +
            "name_finnish VARCHAR(45) NULL, " +
            "name_swedish VARCHAR(45) NULL, " +
            "address_finnish VARCHAR(45) NULL, " +
            "address_swedish VARCHAR(45) NULL, " +
            "city_finnish VARCHAR(45) NULL, " +
            "city_swedish VARCHAR(45) NULL, " +
            "operator VARCHAR(45) NULL, " +
            "capacity INT NULL, " +
            "x DOUBLE NULL, " +
            "y DOUBLE NULL, " +
            "PRIMARY KEY (station_id))";

    jdbcTemplate.execute(createTableQuery);
  };

  @Transactional
  public void saveStationsFromCsv(String csvFilePath) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath), "UTF-8"));
    String line;
    List<Station> stations = new ArrayList<>();

    boolean firstLine = true; 
    while ((line = br.readLine()) != null) {
      System.out.println("Station line is: " + line);
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
            
        if (journeyStationId > 0) {
          Station station = new Station(journeyStationId, name, nameSwedish, address, addressSwedish, city,
                  citySwedish, operator, capacity, x, y);
            
          System.out.println("Station is: " + station);
          stations.add(station);
        }
    
      } catch (NumberFormatException ex) {
        ex.printStackTrace();
      }
    }

    System.out.println("Stations are: " + stations);
    stationRepository.saveAll(stations);
    br.close();
  }
}