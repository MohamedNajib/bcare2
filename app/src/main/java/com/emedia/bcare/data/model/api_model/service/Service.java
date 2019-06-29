
package com.emedia.bcare.data.model.api_model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private SData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SData getData() {
        return data;
    }

    public void setData(SData data) {
        this.data = data;
    }

}
