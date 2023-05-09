package net.virkkunen.helsinkicitybikeapp.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Journey {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="journey_id")
  private Integer journeyId;
  @Column(name="departure")
  private LocalDateTime departureTime;
  @Column(name="return")
  private LocalDateTime returnTime;
  @Column(name="departure_station_id")
  private Integer departureStationId;
  @Column(name="departure_station_name")
  private String departureStationName;
  @Column(name="return_station_id")
  private Integer returnStationId;
  @Column(name="return_station_name")
  private String returnStationName;
  @Column(name="distance")
  private Integer distance;
  @Column(name="duration")
  private Integer duration;
  
  public Journey(Integer journeyId, LocalDateTime departureTime, LocalDateTime returnTime, Integer departureStationId,
      String departureStationName, Integer returnStationId, String returnStationName, Integer distance,
      Integer duration) {
    this.journeyId = journeyId;
    this.departureTime = departureTime;
    this.returnTime = returnTime;
    this.departureStationId = departureStationId;
    this.departureStationName = departureStationName;
    this.returnStationId = returnStationId;
    this.returnStationName = returnStationName;
    this.distance = distance;
    this.duration = duration;
  }

  public Integer getJourneyId() {
    return journeyId;
  }

  public void setJourneyId(Integer journeyId) {
    this.journeyId = journeyId;
  }
  
  public LocalDateTime getDepartureTime() {
    return departureTime;
  }
  
  public void setDepartureTime(LocalDateTime departureTime) {
    this.departureTime = departureTime;
  }
  
  public LocalDateTime getReturnTime() {
    return returnTime;
  }
  
  public void setReturnTime(LocalDateTime returnTime) {
    this.returnTime = returnTime;
  }
  
  public Integer getDepartureStationId() {
    return departureStationId;
  }
  
  public void setDepartureStationId(Integer departureStationId) {
    this.departureStationId = departureStationId;
  }
  
  public String getDepartureStationName() {
    return departureStationName;
  }
  
  public void setDepartureStationName(String departureStationName) {
    this.departureStationName = departureStationName;
  }
  
  public Integer getReturnStationId() {
    return returnStationId;
  }
  
  public void setReturnStationId(Integer returnStationId) {
    this.returnStationId = returnStationId;
  }
  
  public String getReturnStationName() {
    return returnStationName;
  }
  
  public void setReturnStationName(String returnStationName) {
    this.returnStationName = returnStationName;
  }
  
  public Integer getDistance() {
    return distance;
  }
  
  public void setDistance(Integer distance) {
    this.distance = distance;
  }
  
  public Integer getDuration() {
    return duration;
  }
  
  public void setDuration(Integer duration) {
    this.duration = duration;
  }

}
