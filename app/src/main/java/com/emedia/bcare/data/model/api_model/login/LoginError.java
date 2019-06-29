package com.emedia.bcare.data.model.api_model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginError {
    @SerializedName("password")
    @Expose
    private List<String> password = null;

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

}
