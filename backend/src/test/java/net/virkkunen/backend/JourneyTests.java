package net.virkkunen.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.virkkunen.backend.entities.Journey;

public class JourneyTests {

  private Journey journey;

  @BeforeEach
  public void init() {
    journey = new Journey(
      LocalDateTime.parse("2023-06-07T05:47:20.000"),
      LocalDateTime.parse("2023-06-07T05:55:40.000"),
      1,
      "Keskustori",
      2,
      "Pispalanharju",
      2500,
      500
    );    
    System.out.println("** New Test **");
  }

  @Test
  public void getDepartureTimeTest() {
    LocalDateTime expected = LocalDateTime.parse("2023-06-07T05:47:20.000");
    LocalDateTime actual = journey.getDepartureTime();

    assertEquals(expected, actual);
  }

  @Test
  public void setDepartureTimeTest() {
    journey.setDepartureTime(LocalDateTime.parse("2022-06-07T05:47:20.000"));
    LocalDateTime expected = LocalDateTime.parse("2022-06-07T05:47:20.000");
    LocalDateTime actual = journey.getDepartureTime();

    assertEquals(expected, actual);
  }

  @Test
  public void getReturnTimeTest() {
    LocalDateTime expected = LocalDateTime.parse("2023-06-07T05:55:40.000");
    LocalDateTime actual = journey.getReturnTime();

    assertEquals(expected, actual);
  }

  @Test
  public void setReturnTimeTest() {
    journey.setReturnTime(LocalDateTime.parse("2022-06-07T05:55:40.000"));
    LocalDateTime expected = LocalDateTime.parse("2022-06-07T05:55:40.000");
    LocalDateTime actual = journey.getReturnTime();

    assertEquals(expected, actual);
  }

  @Test
  public void getDepartureStationIdTest() {
    int expected = 1;
    int actual = journey.getDepartureStationId();

    assertEquals(expected, actual);
  }

  @Test
  public void setDepartureStationIdTest() {
    journey.setDepartureStationId(11);
    int expected = 11;
    int actual = journey.getDepartureStationId();

    assertEquals(expected, actual);
  }

  @Test
  public void getDepartureStationNameTest() {
    String expected = "Keskustori";
    String actual = journey.getDepartureStationName();

    assertEquals(expected, actual);
  }

  @Test
  public void setDepartureStationNameTest() {
    journey.setDepartureStationName("Tammelantori");
    String expected = "Tammelantori";
    String actual = journey.getDepartureStationName();

    assertEquals(expected, actual);
  }

  @Test
  public void getReturnStationIdTest() {
    int expected = 2;
    int actual = journey.getReturnStationId();

    assertEquals(expected, actual);
  }

  @Test
  public void setReturnStationIdTest() {
    journey.setReturnStationId(22);
    int expected = 22;
    int actual = journey.getReturnStationId();

    assertEquals(expected, actual);
  }

  @Test
  public void getReturnStationNameTest() {
    String expected = "Pispalanharju";
    String actual = journey.getReturnStationName();

    assertEquals(expected, actual);
  }

  @Test
  public void setReturnStationNameTest() {
    journey.setReturnStationName("Tahmela");
    String expected = "Tahmela";
    String actual = journey.getReturnStationName();

    assertEquals(expected, actual);
  }

  @Test
  public void getDistanceTest() {
    int expected = 2500;
    int actual = journey.getDistance();

    assertEquals(expected, actual);
  }

  @Test
  public void setDistanceTest() {
    journey.setDistance(10000);
    int expected = 10000;
    int actual = journey.getDistance();

    assertEquals(expected, actual);
  }

  @Test
  public void getDurationTest() {
    int expected = 500;
    int actual = journey.getDuration();

    assertEquals(expected, actual);
  }

  @Test
  public void setDurationTest() {
    journey.setDuration(10);
    int expected = 10;
    int actual = journey.getDuration();

    assertEquals(expected, actual);
  }

  @Test
  public void hashCodeTest() {
    Journey secondJourney = new Journey(
      LocalDateTime.parse("2023-06-07T05:47:20.000"),
      LocalDateTime.parse("2023-06-07T05:55:40.000"),
      1,
      "Keskustori",
      2,
      "Pispalanharju",
      2500,
      500
    );  

    assertEquals(journey.hashCode(), secondJourney.hashCode());
  }

  @Test
  public void equalsTest() {
    Journey secondJourney = new Journey(
      LocalDateTime.parse("2023-06-07T05:47:20.000"),
      LocalDateTime.parse("2023-06-07T05:55:40.000"),
      1,
      "Keskustori",
      2,
      "Pispalanharju",
      2500,
      500
    ); 

    assertTrue(journey.equals(secondJourney));
    assertTrue(secondJourney.equals(journey));
  }

  @Test
  public void toStringTest() {
    String expected = "Journey [departureTime=2023-06-07T05:47:20, returnTime=2023-06-07T05:55:40, " +
    "departureStationName=Keskustori, returnStationName=Pispalanharju]";
    String actual = journey.toString();

    assertEquals(expected, actual);
  }

}
