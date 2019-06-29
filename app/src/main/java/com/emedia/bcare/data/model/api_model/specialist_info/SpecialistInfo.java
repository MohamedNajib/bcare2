
package com.emedia.bcare.data.model.api_model.specialist_info;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialistInfo {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<SpecialistData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SpecialistData> getData() {
        return data;
    }

    public void setData(List<SpecialistData> data) {
        this.data = data;
    }

}
