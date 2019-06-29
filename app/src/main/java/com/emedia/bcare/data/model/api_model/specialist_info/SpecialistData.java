
package com.emedia.bcare.data.model.api_model.specialist_info;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialistData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("specialist_rate")
    @Expose
    private String specialistRate;
    @SerializedName("specialist_review")
    @Expose
    private List<SpecialistReview> specialistReview = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialistRate() {
        return specialistRate;
    }

    public void setSpecialistRate(String specialistRate) {
        this.specialistRate = specialistRate;
    }

    public List<SpecialistReview> getSpecialistReview() {
        return specialistReview;
    }

    public void setSpecialistReview(List<SpecialistReview> specialistReview) {
        this.specialistReview = specialistReview;
    }

}
