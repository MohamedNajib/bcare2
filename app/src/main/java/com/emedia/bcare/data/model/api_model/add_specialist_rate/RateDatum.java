
package com.emedia.bcare.data.model.api_model.add_specialist_rate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateDatum {

    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
