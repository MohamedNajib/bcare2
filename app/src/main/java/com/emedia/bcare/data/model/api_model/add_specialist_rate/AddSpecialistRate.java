
package com.emedia.bcare.data.model.api_model.add_specialist_rate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddSpecialistRate {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<RateDatum> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RateDatum> getData() {
        return data;
    }

    public void setData(List<RateDatum> data) {
        this.data = data;
    }

}
