package com.example.usman.proretro.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usman on 04/04/2018.
 */

public class Location {
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("langi")
    @Expose
    private double langi;
    @SerializedName("date")
    @Expose
    private String date;

    public Location(Long locationId, String driverId, double lat, double langi, String date) {
        this.locationId = locationId;
        this.driverId = driverId;
        this.lat = lat;
        this.langi = langi;
        this.date = date;
    }

    public Location(String driverId, double lat, double langi, String date) {
        this.driverId = driverId;
        this.lat = lat;
        this.langi = langi;
        this.date = date;
    }

    public Long getLocationId() {        return locationId;    }

    public void setLocationId(Long locationId) {        this.locationId = locationId;    }

    public String getDriverId() {        return driverId;    }

    public void setDriverId(String driverId) {       this.driverId = driverId;    }

    public double getLat() {        return lat;    }

    public void setLat(double lat) {      this.lat = lat;    }

    public double getLangi() {  return langi;  }

    public void setLangi(double langi) {   this.langi = langi;   }

    public String getDate() { return date;}

    public void setDate(String date) {  this.date = date;  }
}
