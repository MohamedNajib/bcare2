
package com.emedia.bcare.data.model.salon_services;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalonServices {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<SalonServicesData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SalonServicesData> getData() {
        return data;
    }

    public void setData(List<SalonServicesData> data) {
        this.data = data;
    }

}
