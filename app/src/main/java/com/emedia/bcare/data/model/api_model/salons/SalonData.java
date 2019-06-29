
package com.emedia.bcare.data.model.api_model.salons;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalonData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("min_price")
    @Expose
    private String minPrice;
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
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("hasHomeService")
    @Expose
    private String hasHomeService;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("isFavorite")
    @Expose
    private Object isFavorite;
    @SerializedName("salon_rate")
    @Expose
    private Object salonRate;
    @SerializedName("count_rate")
    @Expose
    private Object countRate;
    @SerializedName("copounNumber")
    @Expose
    private Object copounNumber;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("share_link")
    @Expose
    private String shareLink;
    @SerializedName("salon_image")
    @Expose
    private List<SalonImage> salonImage = null;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getHasHomeService() {
        return hasHomeService;
    }

    public void setHasHomeService(String hasHomeService) {
        this.hasHomeService = hasHomeService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Object isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Object getSalonRate() {
        return salonRate;
    }

    public void setSalonRate(Object salonRate) {
        this.salonRate = salonRate;
    }

    public Object getCountRate() {
        return countRate;
    }

    public void setCountRate(Object countRate) {
        this.countRate = countRate;
    }

    public Object getCopounNumber() {
        return copounNumber;
    }

    public void setCopounNumber(Object copounNumber) {
        this.copounNumber = copounNumber;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public List<SalonImage> getSalonImage() {
        return salonImage;
    }

    public void setSalonImage(List<SalonImage> salonImage) {
        this.salonImage = salonImage;
    }

}
