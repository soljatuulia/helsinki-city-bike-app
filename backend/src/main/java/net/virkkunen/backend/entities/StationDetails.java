package net.virkkunen.backend.entities;

public class StationDetails {
  
  private String name;
  private String address;
  private int totalDepartures;
  private int totalReturns;

  public StationDetails(String name, String address, int totalDepartures, int totalReturns) {
    this.name = name;
    this.address = address;
    this.totalDepartures = totalDepartures;
    this.totalReturns = totalReturns;
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

  

}
