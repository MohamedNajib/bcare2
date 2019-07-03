
package com.emedia.bcare.data.model.salon_reserve;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalonReserve {


    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<ReserveAt> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ReserveAt> getData() {
        return data;
    }

    public void setData(List<ReserveAt> data) {
        this.data = data;
    }

}
