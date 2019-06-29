package com.emedia.bcare.data.model.api_model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginErrorMain {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<LoginError> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<LoginError> getData() {
        return data;
    }

    public void setData(List<LoginError> data) {
        this.data = data;
    }
}
