package net.virkkunen.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.virkkunen.backend.entities.StationDetails;

public class StationDetailsTests {

  private StationDetails details;

  @BeforeEach
  public void init() {
    details = new StationDetails(
      "Keskustori",
      "Keskustori 1",
      1000,
      1000,
      2.5,
      1.5
    );
    System.out.println("** New Test **");
  }

  @Test
  public void getStationNameTest() {
    String expected = "Keskustori";
    String actual = details.getName();

    assertEquals(expected, actual);
  }

  @Test
  public void setStationNameTest() {
    details.setName("Pispalanharju");
    String expected = "Pispalanharju";
    String actual = details.getName();

    assertEquals(expected, actual);
  }  
  
  @Test
  public void getStationAddressTest() {
    String expected = "Keskustori 1";
    String actual = details.getAddress();

    assertEquals(expected, actual);
  }

  @Test
  public void setStationAddressTest() {
    details.setAddress("Pispalanharju 1");
    String expected = "Pispalanharju 1";
    String actual = details.getAddress();

    assertEquals(expected, actual);
  }
  
  @Test
  public void getTotalDeparturesTest() {
    int expected = 1000;
    int actual = details.getTotalDepartures();

    assertEquals(expected, actual);
  }

  @Test
  public void setTotalDeparturesTest() {
    details.setTotalDepartures(2);
    int expected = 2;
    int actual = details.getTotalDepartures();

    assertEquals(expected, actual);
  }
  
  @Test
  public void getTotalReturnsTest() {
    int expected = 1000;
    int actual = details.getTotalReturns();

    assertEquals(expected, actual);
  }

  @Test
  public void setTotalReturnsTest() {
    details.setTotalReturns(10);
    int expected = 10;
    int actual = details.getTotalReturns();

    assertEquals(expected, actual);
  }

  @Test
  public void getAverageDepartureDistanceTest() {
    double expected = 2.5;
    double actual = details.getAverageDepartureDistance();

    assertEquals(expected, actual);
  }

  @Test
  public void setAverageDepartureDistanceTest() {
    details.setAverageDepartureDistance(10);
    double expected = 10;
    double actual = details.getAverageDepartureDistance();

    assertEquals(expected, actual);
  }

  @Test
  public void getAverageReturnDistanceTest() {
    double expected = 1.5;
    double actual = details.getAverageReturnDistance();

    assertEquals(expected, actual);
  }

  @Test
  public void setAverageReturnDistanceTest() {
    details.setAverageReturnDistance(10.0);
    double expected = 10.0;
    double actual = details.getAverageReturnDistance();

    assertEquals(expected, actual);
  }
}
