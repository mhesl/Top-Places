package com.example.nearbyplaces.nearbyplaces;

public class ViewModel {

    private String name;
    private String streetName;
    private String url;

    public ViewModel(String name, String streetName, String url) {
        this.name = name;
        this.streetName = streetName;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
