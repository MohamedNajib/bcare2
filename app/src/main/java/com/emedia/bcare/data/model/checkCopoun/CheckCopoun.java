
package com.emedia.bcare.data.model.checkCopoun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckCopoun {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private CopounData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CopounData getData() {
        return data;
    }

    public void setData(CopounData data) {
        this.data = data;
    }

}
