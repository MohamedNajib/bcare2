
package com.emedia.bcare.data.model.api_model.forgotPass_sendmail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassSendMail {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<EmailDatum> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<EmailDatum> getData() {
        return data;
    }

    public void setData(List<EmailDatum> data) {
        this.data = data;
    }

}
