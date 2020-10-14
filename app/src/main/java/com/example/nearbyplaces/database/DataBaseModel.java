package com.example.nearbyplaces.database;

public class DataBaseModel {
    private String venueName;
    private String placeName;

    public DataBaseModel(String venueName, String placeName) {
        this.venueName = venueName;
        this.placeName = placeName;
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
