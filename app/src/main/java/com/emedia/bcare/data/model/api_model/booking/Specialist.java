
package com.emedia.bcare.data.model.api_model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Specialist {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("share_link")
    @Expose
    private String shareLink;
    @SerializedName("specialist")
    @Expose
    private List<Specialist_> specialist = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public List<Specialist_> getSpecialist() {
        return specialist;
    }

    public void setSpecialist(List<Specialist_> specialist) {
        this.specialist = specialist;
    }

}
