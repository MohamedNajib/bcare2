
package com.emedia.bcare.data.model.api_model.my_event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalonServiceDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

}
