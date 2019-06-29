
package com.emedia.bcare.data.model.api_model.salons;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Salons {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<SalonData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SalonData> getData() {
        return data;
    }

    public void setData(List<SalonData> data) {
        this.data = data;
    }

}
