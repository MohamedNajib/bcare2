
package com.emedia.bcare.data.model.logoutUser;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutUser {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<Logout> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Logout> getData() {
        return data;
    }

    public void setData(List<Logout> data) {
        this.data = data;
    }

}
