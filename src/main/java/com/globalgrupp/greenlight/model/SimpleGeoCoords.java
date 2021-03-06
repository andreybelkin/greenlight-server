package com.globalgrupp.greenlight.model;

import java.io.Serializable;

/**
 * Created by п on 24.12.2015.
 */
public class SimpleGeoCoords implements Serializable {
    private double longitude;

    private double latitude;

    private double altitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public SimpleGeoCoords(double longitude, double latitude, double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public SimpleGeoCoords() {
    }

    private Long radius;

    public Long getRadius() {
        return radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }
}
