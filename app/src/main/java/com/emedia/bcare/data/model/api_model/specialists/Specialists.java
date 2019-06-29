
package com.emedia.bcare.data.model.api_model.specialists;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialists {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<SpecialistsData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SpecialistsData> getData() {
        return data;
    }

    public void setData(List<SpecialistsData> data) {
        this.data = data;
    }

}
