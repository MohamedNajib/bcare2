package com.emedia.bcare.data.model.api_model.booking;

public class MapModel {

    String flat;
    String building;
    String landmark;
    String addressPnned;
    String lat;
    String lng;

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setAddressPnned(String addressPnned) {
        this.addressPnned = addressPnned;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getAddressPnned() {
        return addressPnned;
    }

    public String getBuilding() {
        return building;
    }

    public String getFlat() {
        return flat;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
