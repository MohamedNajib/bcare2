
package com.emedia.bcare.data.model.api_model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private HomeData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HomeData getData() {
        return data;
    }

    public void setData(HomeData data) {
        this.data = data;
    }

}
