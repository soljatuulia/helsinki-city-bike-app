package net.virkkunen.backend;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import net.virkkunen.backend.repositories.JourneyRepository;
import net.virkkunen.backend.services.JourneyService;

public class JourneyServiceTests {

  @Mock
  private JourneyRepository journeyRepository;

  private JourneyService journeyService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    journeyService = new JourneyService(journeyRepository);
  }  

  private String getAbsolutePath(String relativePath) {
    Path resourceDirectory = Paths.get("src", "test", "java", "net", "virkkunen", "backend", "resources", relativePath);
    return resourceDirectory.toAbsolutePath().toString();
  }

  @Test
  public void validDataIsAddedToRepository() throws IOException {
      JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);
      JourneyService journeyService = new JourneyService(journeyRepository);
      String csvFilePath = getAbsolutePath("journeyTests_validData.csv");

      journeyService.saveJourneysFromCsv(csvFilePath);

      Mockito.verify(journeyRepository, Mockito.times(1)).saveAll(Mockito.anyList());
  }

  @Test
  void invalidDepartureTimeShouldThrowDateTimeParseException() {
    String csvFilePath = getAbsolutePath("journeyTests_invalidDepartureTime.csv");

    Assertions.assertThrows(DateTimeParseException.class, () -> {
        journeyService.saveJourneysFromCsv(csvFilePath);
    });
  }

  @Test
  void invalidReturnTimeShouldThrowDateTimeParseException() {
    String csvFilePath = getAbsolutePath("journeyTests_invalidReturnTime.csv");

    Assertions.assertThrows(DateTimeParseException.class, () -> {
          journeyService.saveJourneysFromCsv(csvFilePath);
      });
  }

  @Test
  void returnTimeBeforeDepartureTimeShouldThrowIllegalArgumentException() {
    String csvFilePath = getAbsolutePath("journeyTests_returnBeforeDeparture.csv");

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
          journeyService.saveJourneysFromCsv(csvFilePath);
      });
  }  

  @Test
  public void invalidDepartureStationIdShouldThrowIllegalArgumentException() {
      String csvFilePath = getAbsolutePath("journeyTests_invalidDepartureStationId.csv");

      Assertions.assertThrows(IllegalArgumentException.class, () -> {
        journeyService.saveJourneysFromCsv(csvFilePath);
    });
  }

  @Test
  public void invalidReturnStationIdShouldThrowIllegalArgumentException() {
      String csvFilePath = getAbsolutePath("journeyTests_invalidReturnStationId.csv");

      Assertions.assertThrows(IllegalArgumentException.class, () -> {
        journeyService.saveJourneysFromCsv(csvFilePath);
    });
  }

  @Test
  public void invalidDistanceShouldThrowIllegalArgumentException() {
      String csvFilePath = getAbsolutePath("journeyTests_invalidDistance.csv");

      Assertions.assertThrows(IllegalArgumentException.class, () -> {
        journeyService.saveJourneysFromCsv(csvFilePath);
    });
  }

  @Test
  public void invalidDurationShouldThrowIllegalArgumentException() {
      String csvFilePath = getAbsolutePath("journeyTests_invalidDuration.csv");

      Assertions.assertThrows(IllegalArgumentException.class, () -> {
        journeyService.saveJourneysFromCsv(csvFilePath);
    });
  }

}
