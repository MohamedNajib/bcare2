package com.emedia.bcare.data.model.api_model.booking;

public class GetReserve {
    String token;
    String lang;
    String salon_id;
    String total_price;
    String discount_percentage;
    String price_after_discount;
    String reservation_date;
    String client_name;
    String client_mobile;
    String place;
    String []services_id;
    String home_latitude;
    String home_longitude;
    String building_number;
    String flat_number;
    String landmark;
    String specialist_id;

    public void setToken(String token) {
        this.token = token;
    }

    public void setClient_mobile(String client_mobile) {
        this.client_mobile = client_mobile;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void setDiscount_percentage(String discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public void setHome_latitude(String home_latitude) {
        this.home_latitude = home_latitude;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPrice_after_discount(String price_after_discount) {
        this.price_after_discount = price_after_discount;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }

    public void setHome_longitude(String home_longitude) {
        this.home_longitude = home_longitude;
    }

    public void setFlat_number(String flat_number) {
        this.flat_number = flat_number;
    }

    public void setSalon_id(String salon_id) {
        this.salon_id = salon_id;
    }

    public void setServices_id(String[] services_id) {
        this.services_id = services_id;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public void setSpecialist_id(String specialist_id) {
        this.specialist_id = specialist_id;
    }

    public String[] getServices_id() {
        return services_id;
    }

    public String getToken() {
        return token;
    }

    public String getClient_mobile() {
        return client_mobile;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getDiscount_percentage() {
        return discount_percentage;
    }

    public String getLang() {
        return lang;
    }

    public String getBuilding_number() {
        return building_number;
    }

    public String getFlat_number() {
        return flat_number;
    }

    public String getHome_latitude() {
        return home_latitude;
    }

    public String getHome_longitude() {
        return home_longitude;
    }

    public String getPrice_after_discount() {
        return price_after_discount;
    }

    public String getPlace() {
        return place;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public String getSalon_id() {
        return salon_id;
    }

    public String getSpecialist_id() {
        return specialist_id;
    }

    public String getTotal_price() {
        return total_price;
    }

}
