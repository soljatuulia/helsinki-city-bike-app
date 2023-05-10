package net.virkkunen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Station {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="station_id")
  private Integer stationId;
  @Column(name="journey_station_id")
  private Integer journeyStationId;  
  @Column(name="name_finnish")
  private String name;  
  @Column(name="name_swedish")
  private String nameSwedish;  
  @Column(name="address_finnish")
  private String address;  
  @Column(name="address_swedish")
  private String addressSwedish;  
  @Column(name="city_finnish")
  private String city;  
  @Column(name="city_swedish")
  private String citySwedish;  
  @Column(name="operator")
  private String operator;  
  @Column(name="capacity")
  private Integer capacity;  
  @Column(name="x")
  private Double x;  
  @Column(name="y")
  private Double y;

  public Integer getStationId() {
    return stationId;
  }
  
  public void setStationId(Integer stationId) {
    this.stationId = stationId;
  }
  
  public Integer getJourneyStationId() {
    return journeyStationId;
  }
  
  public void setJourneyStationId(Integer journeyStationId) {
    this.journeyStationId = journeyStationId;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getNameSwedish() {
    return nameSwedish;
  }
  
  public void setNameSwedish(String nameSwedish) {
    this.nameSwedish = nameSwedish;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getAddressSwedish() {
    return addressSwedish;
  }
  
  public void setAddressSwedish(String addressSwedish) {
    this.addressSwedish = addressSwedish;
  }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getCitySwedish() {
    return citySwedish;
  }
  
  public void setCitySwedish(String citySwedish) {
    this.citySwedish = citySwedish;
  }
  
  public String getOperator() {
    return operator;
  }
  
  public void setOperator(String operator) {
    this.operator = operator;
  }
  
  public Integer getCapacity() {
    return capacity;
  }
  
  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }
  
  public Double getX() {
    return x;
  }
  
  public void setX(Double x) {
    this.x = x;
  }
  
  public Double getY() {
    return y;
  }
  
  public void setY(Double y) {
    this.y = y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
    result = prime * result + ((journeyStationId == null) ? 0 : journeyStationId.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((nameSwedish == null) ? 0 : nameSwedish.hashCode());
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((addressSwedish == null) ? 0 : addressSwedish.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((citySwedish == null) ? 0 : citySwedish.hashCode());
    result = prime * result + ((operator == null) ? 0 : operator.hashCode());
    result = prime * result + ((capacity == null) ? 0 : capacity.hashCode());
    result = prime * result + ((x == null) ? 0 : x.hashCode());
    result = prime * result + ((y == null) ? 0 : y.hashCode());
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
    Station other = (Station) obj;
    if (stationId == null) {
      if (other.stationId != null)
        return false;
    } else if (!stationId.equals(other.stationId))
      return false;
    if (journeyStationId == null) {
      if (other.journeyStationId != null)
        return false;
    } else if (!journeyStationId.equals(other.journeyStationId))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (nameSwedish == null) {
      if (other.nameSwedish != null)
        return false;
    } else if (!nameSwedish.equals(other.nameSwedish))
      return false;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    if (addressSwedish == null) {
      if (other.addressSwedish != null)
        return false;
    } else if (!addressSwedish.equals(other.addressSwedish))
      return false;
    if (city == null) {
      if (other.city != null)
        return false;
    } else if (!city.equals(other.city))
      return false;
    if (citySwedish == null) {
      if (other.citySwedish != null)
        return false;
    } else if (!citySwedish.equals(other.citySwedish))
      return false;
    if (operator == null) {
      if (other.operator != null)
        return false;
    } else if (!operator.equals(other.operator))
      return false;
    if (capacity == null) {
      if (other.capacity != null)
        return false;
    } else if (!capacity.equals(other.capacity))
      return false;
    if (x == null) {
      if (other.x != null)
        return false;
    } else if (!x.equals(other.x))
      return false;
    if (y == null) {
      if (other.y != null)
        return false;
    } else if (!y.equals(other.y))
      return false;
    return true;
  }
 
}