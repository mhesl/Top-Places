package com.example.nearbyplaces.database;

public class DataBaseModel {
    private String venueName;
    private String placeName;
    private String completeAddress;
    private String crossStreet;
    private String city;
    private int distance;


    public DataBaseModel(String venueName, String placeName, String completeAddress, String crossStreet, String city, int distance) {
        this.venueName = venueName;
        this.placeName = placeName;
        this.completeAddress = completeAddress;
        this.crossStreet = crossStreet;
        this.city = city;
        this.distance = distance;
    }


    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
