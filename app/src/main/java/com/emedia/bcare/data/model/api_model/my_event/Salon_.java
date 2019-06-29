
package com.emedia.bcare.data.model.api_model.my_event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Salon_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("salon_name")
    @Expose
    private String salonName;
    @SerializedName("salon_address")
    @Expose
    private String salonAddress;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("share_link")
    @Expose
    private String shareLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

}
