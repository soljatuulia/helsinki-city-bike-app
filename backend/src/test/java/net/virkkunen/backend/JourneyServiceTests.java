package net.virkkunen.backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JourneyServiceTests {

    private boolean validateJourney(String csvLine) {
        String[] fields = csvLine.split(",");
        try {
            LocalDateTime departureTime = LocalDateTime.parse(fields[0], DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime returnTime = LocalDateTime.parse(fields[1], DateTimeFormatter.ISO_DATE_TIME);
            Integer departureStationId = Integer.parseInt(fields[2]);
            String departureStationName = fields[3].replace("\"", "").trim();
            Integer returnStationId = Integer.parseInt(fields[4]);
            String returnStationName = fields[5].replace("\"", "").trim();
            Integer distance = Integer.parseInt(fields[6]);
            Integer duration = Integer.parseInt(fields[7]);

            return departureTime.isBefore(returnTime)
                    && departureStationId > 0
                    && returnStationId > 0
                    && distance >= 10
                    && duration >= 10;

        } catch (NumberFormatException | DateTimeParseException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Test
    public void validateJourney() {
        String csvLine = "2021-05-01T00:00:11,2021-05-01T00:04:34,138,Arabiankatu,138,Arabiankatu,1057,259";

        Assertions.assertTrue(validateJourney(csvLine));
    }

    @Test
    public void rejectInvalidDateTime() {
        String csvLine = "InvalidDateTime,2021-05-01T00:04:34,138,Arabiankatu,138,Arabiankatu,1057,259";

        Assertions.assertFalse(validateJourney(csvLine));
    }

    @Test
    public void rejectArrivalBeforeDeparture() {
        String csvLine = "2021-05-01T00:04:34,2021-05-01T00:00:11,138,Arabiankatu,138,Arabiankatu,1057,259";

        Assertions.assertFalse(validateJourney(csvLine));
    }

    @Test
    public void rejectDurationLessThan10Seconds() {
        String csvLine = "2021-05-01T00:00:11,2021-05-01T00:04:34,138,Arabiankatu,138,Arabiankatu,1057,5";

        Assertions.assertFalse(validateJourney(csvLine));
    }

    @Test
    public void rejectDistanveLessThan10Meters() {
        String csvLine = "2021-05-01T00:00:11,2021-05-01T00:04:34,138,Arabiankatu,138,Arabiankatu,5,1057";

        Assertions.assertFalse(validateJourney(csvLine));
    }

    @Test
    public void rejectInvalidDepartureStationId() {
        String csvLine = "2021-05-01T00:00:11,2021-05-01T00:04:34,-138,Arabiankatu,138,Arabiankatu,1057,259";

        Assertions.assertFalse(validateJourney(csvLine));
    }

    @Test
    public void rejectInvalidReturnStationId() {
        String csvLine = "2021-05-01T00:00:11,2021-05-01T00:04:34,138,Arabiankatu,-138,Arabiankatu,1057,259";

        Assertions.assertFalse(validateJourney(csvLine));
    }
}