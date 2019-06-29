
package com.emedia.bcare.data.model.api_model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("time_at_salon")
    @Expose
    private List<TimeAtSalon> timeAtSalon = null;
    @SerializedName("time_at_home")
    @Expose
    private List<TimeAtHome> timeAtHome = null;
    @SerializedName("specialists")
    @Expose
    private List<Specialist> specialists = null;

    public List<TimeAtSalon> getTimeAtSalon() {
        return timeAtSalon;
    }

    public void setTimeAtSalon(List<TimeAtSalon> timeAtSalon) {
        this.timeAtSalon = timeAtSalon;
    }

    public List<TimeAtHome> getTimeAtHome() {
        return timeAtHome;
    }

    public void setTimeAtHome(List<TimeAtHome> timeAtHome) {
        this.timeAtHome = timeAtHome;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }

    public void setSpecialists(List<Specialist> specialists) {
        this.specialists = specialists;
    }

}
