
package com.emedia.bcare.data.model.api_model.my_event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot_ {

    @SerializedName("reservation_id")
    @Expose
    private String reservationId;
    @SerializedName("salon_service_id")
    @Expose
    private String salonServiceId;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getSalonServiceId() {
        return salonServiceId;
    }

    public void setSalonServiceId(String salonServiceId) {
        this.salonServiceId = salonServiceId;
    }

}
