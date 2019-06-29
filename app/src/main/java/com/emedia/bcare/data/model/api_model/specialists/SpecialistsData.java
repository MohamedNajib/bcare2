
package com.emedia.bcare.data.model.api_model.specialists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialistsData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("specialist_group")
    @Expose
    private String specialistGroup;
    @SerializedName("specialist_rate")
    @Expose
    private String specialistRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialistGroup() {
        return specialistGroup;
    }

    public void setSpecialistGroup(String specialistGroup) {
        this.specialistGroup = specialistGroup;
    }

    public String getSpecialistRate() {
        return specialistRate;
    }

    public void setSpecialistRate(String specialistRate) {
        this.specialistRate = specialistRate;
    }

}
