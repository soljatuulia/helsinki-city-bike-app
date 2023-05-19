package net.virkkunen.backend;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import net.virkkunen.backend.entities.Station;
import net.virkkunen.backend.repositories.StationRepository;
import net.virkkunen.backend.services.StationService;

public class StationServiceTests {

  @Mock
  private StationService stationServiceMock;

  @Mock
  private StationRepository stationRepository;

  @Captor
  private ArgumentCaptor<List<Station>> captor;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    new StationService(stationRepository);
  }  

  private String getAbsolutePath(String relativePath) {
    Path resourceDirectory = Paths.get("src", "test", "java", "net", "virkkunen", "backend", "resources", relativePath);
    return resourceDirectory.toAbsolutePath().toString();
  }

  @Test
  public void validLineIsAddedFromCsvToRepository() throws IOException {
      StationRepository stationRepository = Mockito.mock(StationRepository.class);

      StationService stationService = new StationService(stationRepository);

      String csvFilePath = getAbsolutePath("stationTests_validData.csv");

      String[] fields = {
        "1",
        "501",
        "Hanasaari",
        "Hanaholmen",
        "Hanasaari",
        "Hanasaarenranta 1",
        "Hanaholmsstranden 1",
        "Espoo",
        "Esbo",
        "CityBike Finland",
        "10",
        "24.840319",
        "60.16582"
      };

      stationService.saveStationsFromCsv(csvFilePath);

      Mockito.verify(stationRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Station> capturedStations = captor.getValue();

      Assertions.assertEquals(1, capturedStations.size());
      Station capturedStation = capturedStations.get(0);

      Assertions.assertEquals(Integer.parseInt(fields[1]), capturedStation.getJourneyStationId());
      Assertions.assertEquals(fields[2], capturedStation.getName());
      Assertions.assertEquals(fields[3], capturedStation.getNameSwedish());
      Assertions.assertEquals(fields[5], capturedStation.getAddress());
      Assertions.assertEquals(fields[6], capturedStation.getAddressSwedish());
      Assertions.assertEquals(fields[7], capturedStation.getCity());
      Assertions.assertEquals(fields[8], capturedStation.getCitySwedish());
      Assertions.assertEquals(fields[9], capturedStation.getOperator());
      Assertions.assertEquals(Integer.parseInt(fields[10]), capturedStation.getCapacity());
      Assertions.assertEquals(Double.parseDouble(fields[11]), capturedStation.getX());
      Assertions.assertEquals(Double.parseDouble(fields[12]), capturedStation.getY());
  }
 
  @Test
  public void dataWithInvalidXIsNotAddedToRepository() throws IOException {
      StationRepository stationRepository = Mockito.mock(StationRepository.class);

      StationService stationService = new StationService(stationRepository);

      String csvFilePath = getAbsolutePath("stationTests_invalidX.csv");

      stationService.saveStationsFromCsv(csvFilePath);

      Mockito.verify(stationRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Station> capturedStations = captor.getValue();

      Assertions.assertEquals(0, capturedStations.size());
  }

  @Test
  public void dataWithInvalidYIsNotAddedToRepository() throws IOException {
      StationRepository stationRepository = Mockito.mock(StationRepository.class);

      StationService stationService = new StationService(stationRepository);

      String csvFilePath = getAbsolutePath("stationTests_invalidY.csv");

      stationService.saveStationsFromCsv(csvFilePath);

      Mockito.verify(stationRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Station> capturedStations = captor.getValue();

      Assertions.assertEquals(0, capturedStations.size());
  }

}