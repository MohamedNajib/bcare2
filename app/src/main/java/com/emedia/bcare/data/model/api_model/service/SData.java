
package com.emedia.bcare.data.model.api_model.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SData {

    @SerializedName("services")
    @Expose
    private List<Service_> services = null;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;

    public List<Service_> getServices() {
        return services;
    }

    public void setServices(List<Service_> services) {
        this.services = services;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}
