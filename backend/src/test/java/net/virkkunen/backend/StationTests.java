package net.virkkunen.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.virkkunen.backend.entities.Station;

public class StationTests {

  private Station station;

  @BeforeEach
  public void init() {
    station = new Station(
      100,
      "Keskustori",
      "Centraltorget",
      "Keskustori 1",
      "Centraltorget 1",
      "Tampere",
      "Tammerfors",
      "TKL",
      25,
      23.45,
      61.30
    );    
    System.out.println("** New Test **");
  }

  @Test
  public void getJourneyStationIdTest() {
    int expected = 100;
    int actual = station.getJourneyStationId();

    assertEquals(expected, actual);
  }

  @Test
  public void setJourneyStationIdTest() {
    station.setJourneyStationId(11);
    int expected = 11;
    int actual = station.getJourneyStationId();

    assertEquals(expected, actual);
  }

  @Test
  public void getNameTest() {
    String expected = "Keskustori";
    String actual = station.getName();

    assertEquals(expected, actual);
  }

  @Test
  public void setNameTest() {
    station.setName("Tammelantori");
    String expected = "Tammelantori";
    String actual = station.getName();

    assertEquals(expected, actual);
  }

  @Test
  public void getSwedishNameTest() {
    String expected = "Centraltorget";
    String actual = station.getNameSwedish();

    assertEquals(expected, actual);
  }

  @Test
  public void setSwedishNameTest() {
    station.setNameSwedish("Hötorget");
    String expected = "Hötorget";
    String actual = station.getNameSwedish();

    assertEquals(expected, actual);
  }

  @Test
  public void getAddressTest() {
    String expected = "Keskustori 1";
    String actual = station.getAddress();

    assertEquals(expected, actual);
  }

  @Test
  public void setAddressTest() {
    station.setAddress("Keskustori 2");
    String expected = "Keskustori 2";
    String actual = station.getAddress();

    assertEquals(expected, actual);
  }

  @Test
  public void getSwedishAddressTest() {
    String expected = "Centraltorget 1";
    String actual = station.getAddressSwedish();

    assertEquals(expected, actual);
  }

  @Test
  public void setSwedishAddressTest() {
    station.setAddressSwedish("Keskustori 1");
    String expected = "Keskustori 1";
    String actual = station.getAddressSwedish();

    assertEquals(expected, actual);
  }

  @Test
  public void getCityTest() {
    String expected = "Tampere";
    String actual = station.getCity();

    assertEquals(expected, actual);
  }

  @Test
  public void setCityTest() {
    station.setCity("Turku");
    String expected = "Turku";
    String actual = station.getCity();

    assertEquals(expected, actual);
  }

  @Test
  public void getSwedishCityTest() {
    String expected = "Tammerfors";
    String actual = station.getCitySwedish();

    assertEquals(expected, actual);
  }

  @Test
  public void setSwedishCityTest() {
    station.setCitySwedish("Åbo");
    String expected = "Åbo";
    String actual = station.getCitySwedish();

    assertEquals(expected, actual);
  }

  @Test
  public void getOperatorTest() {
    String expected = "TKL";
    String actual = station.getOperator();

    assertEquals(expected, actual);
  }

  @Test
  public void setOperatorTest() {
    station.setOperator("HSL");
    String expected = "HSL";
    String actual = station.getOperator();

    assertEquals(expected, actual);
  }

  @Test
  public void getCapacityTest() {
    int expected = 25;
    int actual = station.getCapacity();

    assertEquals(expected, actual);
  }

  @Test
  public void setCapacityTest() {
    station.setCapacity(200);
    int expected = 200;
    int actual = station.getCapacity();

    assertEquals(expected, actual);
  }

  @Test
  public void getXTest() {
    double expected = 23.45;
    double actual = station.getX();

    assertEquals(expected, actual);
  }

  @Test
  public void setXTest() {
    station.setX(60.0);
    double expected = 60.0;
    double actual = station.getX();

    assertEquals(expected, actual);
  }

  @Test
  public void getYTest() {
    double expected = 61.30;
    double actual = station.getY();

    assertEquals(expected, actual);
  }

  @Test
  public void setYTest() {
    station.setY(60.0);
    double expected = 60.0;
    double actual = station.getY();

    assertEquals(expected, actual);
  }

  @Test
  public void hashCodeTest() {
    Station secondStation = new Station(
      100,
      "Keskustori",
      "Centraltorget",
      "Keskustori 1",
      "Centraltorget 1",
      "Tampere",
      "Tammerfors",
      "TKL",
      25,
      23.45,
      61.30
    );  

    assertEquals(station.hashCode(), secondStation.hashCode());
  }

  @Test
  public void equalsTest() {
    Station secondStation = new Station(
      100,
      "Keskustori",
      "Centraltorget",
      "Keskustori 1",
      "Centraltorget 1",
      "Tampere",
      "Tammerfors",
      "TKL",
      25,
      23.45,
      61.30
    );  

    assertTrue(station.equals(secondStation));
    assertTrue(secondStation.equals(station));
  }

  @Test
  public void toStringTest() {
    String expected = "Station [name=Keskustori, address=Keskustori 1, city=Tampere]";
    String actual = station.toString();

    assertEquals(expected, actual);
  }
  
}
