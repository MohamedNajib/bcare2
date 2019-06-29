
package com.emedia.bcare.data.model.api_model.cities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cities {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<CitiesDatum> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CitiesDatum> getData() {
        return data;
    }

    public void setData(List<CitiesDatum> data) {
        this.data = data;
    }

}
