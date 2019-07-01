
package com.emedia.bcare.data.model.change_password;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassword {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<ChangePasswordData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ChangePasswordData> getData() {
        return data;
    }

    public void setData(List<ChangePasswordData> data) {
        this.data = data;
    }

}
