
package com.emedia.bcare.data.model.login_email;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginEmail {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<UserData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserData> getData() {
        return data;
    }

    public void setData(List<UserData> data) {
        this.data = data;
    }

}
