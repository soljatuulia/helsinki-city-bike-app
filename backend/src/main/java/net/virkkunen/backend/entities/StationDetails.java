package net.virkkunen.backend.entities;

public class StationDetails {
  
  private String name;
  private String address;
  private int totalDepartures;
  private int totalReturns;
  private double averageDepartureDistance;
  private double averageReturnDistance;

  public StationDetails(String name, String address, int totalDepartures, int totalReturns,
      double averageDepartureDistance, double averageReturnDistance) {
    this.name = name;
    this.address = address;
    this.totalDepartures = totalDepartures;
    this.totalReturns = totalReturns;
    this.averageDepartureDistance = averageDepartureDistance;
    this.averageReturnDistance = averageReturnDistance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getTotalDepartures() {
    return totalDepartures;
  }

  public void setTotalDepartures(int totalDepartures) {
    this.totalDepartures = totalDepartures;
  }

  public int getTotalReturns() {
    return totalReturns;
  }

  public void setTotalReturns(int totalReturns) {
    this.totalReturns = totalReturns;
  }

  public double getAverageDepartureDistance() {
    return averageDepartureDistance;
  }

  public void setAverageDepartureDistance(double averageDepartureDistance) {
    this.averageDepartureDistance = averageDepartureDistance;
  }

  public double getAverageReturnDistance() {
    return averageReturnDistance;
  }

  public void setAverageReturnDistance(double averageReturnDistance) {
    this.averageReturnDistance = averageReturnDistance;
  }

}
