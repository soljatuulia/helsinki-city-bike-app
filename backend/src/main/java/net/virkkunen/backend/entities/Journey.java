package net.virkkunen.backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="journey")
public class Journey {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "journey_id")
  private Integer journeyId;
  @Column(name = "departure_time")
  private LocalDateTime departureTime;
  @Column(name = "return_time")
  private LocalDateTime returnTime;
  @Column(name = "departure_station_id")
  private Integer departureStationId;
  @Column(name = "departure_station_name", nullable = false)
  private String departureStationName;
  @Column(name = "return_station_id")
  private Integer returnStationId;
  @Column(name = "return_station_name", nullable = false)
  private String returnStationName;
  @Min(10)
  @Column(name = "distance")
  private Integer distance;
  @Min(10)
  @Column(name = "duration")
  private Integer duration;

  public Journey(LocalDateTime departureTime, LocalDateTime returnTime, Integer departureStationId,
      String departureStationName, Integer returnStationId, String returnStationName, Integer distance,
      Integer duration) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((journeyId == null) ? 0 : journeyId.hashCode());
    result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
    result = prime * result + ((returnTime == null) ? 0 : returnTime.hashCode());
    result = prime * result + ((departureStationId == null) ? 0 : departureStationId.hashCode());
    result = prime * result + ((departureStationName == null) ? 0 : departureStationName.hashCode());
    result = prime * result + ((returnStationId == null) ? 0 : returnStationId.hashCode());
    result = prime * result + ((returnStationName == null) ? 0 : returnStationName.hashCode());
    result = prime * result + ((distance == null) ? 0 : distance.hashCode());
    result = prime * result + ((duration == null) ? 0 : duration.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Journey other = (Journey) obj;
    if (journeyId == null) {
      if (other.journeyId != null)
        return false;
    } else if (!journeyId.equals(other.journeyId))
      return false;
    if (departureTime == null) {
      if (other.departureTime != null)
        return false;
    } else if (!departureTime.equals(other.departureTime))
      return false;
    if (returnTime == null) {
      if (other.returnTime != null)
        return false;
    } else if (!returnTime.equals(other.returnTime))
      return false;
    if (departureStationId == null) {
      if (other.departureStationId != null)
        return false;
    } else if (!departureStationId.equals(other.departureStationId))
      return false;
    if (departureStationName == null) {
      if (other.departureStationName != null)
        return false;
    } else if (!departureStationName.equals(other.departureStationName))
      return false;
    if (returnStationId == null) {
      if (other.returnStationId != null)
        return false;
    } else if (!returnStationId.equals(other.returnStationId))
      return false;
    if (returnStationName == null) {
      if (other.returnStationName != null)
        return false;
    } else if (!returnStationName.equals(other.returnStationName))
      return false;
    if (distance == null) {
      if (other.distance != null)
        return false;
    } else if (!distance.equals(other.distance))
      return false;
    if (duration == null) {
      if (other.duration != null)
        return false;
    } else if (!duration.equals(other.duration))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Journey [departureTime=" + departureTime + ", returnTime=" + returnTime + ", departureStationName="
        + departureStationName + ", returnStationName=" + returnStationName + "]";
  }

  

}
