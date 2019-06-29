
package com.emedia.bcare.data.model.api_model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("salon_id")
    @Expose
    private String salonId;
    @SerializedName("specialist_id")
    @Expose
    private String specialistId;

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

}
