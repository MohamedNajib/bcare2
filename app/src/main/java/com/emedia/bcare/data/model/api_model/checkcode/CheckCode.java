package com.emedia.bcare.data.model.api_model.checkcode;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckCode {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<CheckCodeDatum> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CheckCodeDatum> getData() {
        return data;
    }

    public void setData(List<CheckCodeDatum> data) {
        this.data = data;
    }

}
