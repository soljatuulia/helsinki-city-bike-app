package net.virkkunen.backend;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import net.virkkunen.backend.entities.Journey;
import net.virkkunen.backend.repositories.JourneyRepository;
import net.virkkunen.backend.services.JourneyService;

public class JourneyServiceTests {

  @Mock
  private JourneyRepository journeyRepository;

  @Captor
  private ArgumentCaptor<List<Journey>> captor;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    new JourneyService(journeyRepository);
  }  

  private String getAbsolutePath(String relativePath) {
    Path resourceDirectory = Paths.get("src", "test", "java", "net", "virkkunen", "backend", "resources", relativePath);
    return resourceDirectory.toAbsolutePath().toString();
  }

  @Test
  public void validLineIsAddedFromCsvToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_validData.csv");

      String[] fields = {
        "2021-06-30T23:59:36",
        "2021-07-01T00:06:21",
        "123",
        "Tenholantie",
        "234",
        "Esterinportti",
        "1234",
        "407"
      };

      journeyService.saveJourneysFromCsv(csvFilePath);


      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(1, capturedJourneys.size());
      Journey capturedJourney = capturedJourneys.get(0);

      Assertions.assertEquals(LocalDateTime.parse(fields[0]), capturedJourney.getDepartureTime());
      Assertions.assertEquals(LocalDateTime.parse(fields[1]), capturedJourney.getReturnTime());
      Assertions.assertEquals(Integer.parseInt(fields[2]), capturedJourney.getDepartureStationId());
      Assertions.assertEquals(fields[3], capturedJourney.getDepartureStationName());
      Assertions.assertEquals(Integer.parseInt(fields[4]), capturedJourney.getReturnStationId());
      Assertions.assertEquals(fields[5], capturedJourney.getReturnStationName());
      Assertions.assertEquals(Integer.parseInt(fields[6]), capturedJourney.getDistance());
      Assertions.assertEquals(Integer.parseInt(fields[7]), capturedJourney.getDuration());
  }

  @Test
  public void dataWithInvalidDepartureStationIdIsNotAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_invalidDepartureStationId.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(0, capturedJourneys.size());
  }

  @Test
  public void dataWithInvalidReturnStationIdIsNotAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_invalidReturnStationId.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(0, capturedJourneys.size());
  }

  @Test
  public void dataWithInvalidDepartureTimeIsNotAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_invalidDepartureTime.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(0, capturedJourneys.size());
  }

  @Test
  public void dataWithInvalidReturnTimeIsNotAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_invalidReturnTime.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(0, capturedJourneys.size());
  }

  @Test
  public void dataWithInvalidDistanceIsNotAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_invalidDistance.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(0, capturedJourneys.size());
  }

  @Test
  public void dataWithInvalidDurationIsNotAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_invalidDuration.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(0, capturedJourneys.size());
  }

  @Test
  public void dataWithReturnBeforeDepartureIsNotAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);

      JourneyService journeyService = new JourneyService(journeyRepository);

      String csvFilePath = getAbsolutePath("journeyTests_returnBeforeDeparture.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(captor.capture());

      List<Journey> capturedJourneys = captor.getValue();

      Assertions.assertEquals(0, capturedJourneys.size());
  }

}
