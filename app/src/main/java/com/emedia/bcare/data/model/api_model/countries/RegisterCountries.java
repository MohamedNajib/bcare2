
package com.emedia.bcare.data.model.api_model.countries;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterCountries {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<CountriesData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CountriesData> getData() {
        return data;
    }

    public void setData(List<CountriesData> data) {
        this.data = data;
    }

}
