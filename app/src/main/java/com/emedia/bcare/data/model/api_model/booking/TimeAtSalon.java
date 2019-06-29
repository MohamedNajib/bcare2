
package com.emedia.bcare.data.model.api_model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeAtSalon {

    public boolean isCheced() {
        return isChecked;
    }

    public boolean isChecked;

    @SerializedName("time")
    @Expose
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
