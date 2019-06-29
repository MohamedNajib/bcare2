
package com.emedia.bcare.data.model.api_model.home;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeData {

    @SerializedName("advertisements")
    @Expose
    private List<Advertisement> advertisements = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("specialists")
    @Expose
    private List<Specialist> specialists = null;

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }

    public void setSpecialists(List<Specialist> specialists) {
        this.specialists = specialists;
    }

}
