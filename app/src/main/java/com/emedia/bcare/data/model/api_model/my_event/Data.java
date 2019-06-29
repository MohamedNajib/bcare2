
package com.emedia.bcare.data.model.api_model.my_event;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("previousAppointment")
    @Expose
    private List<PreviousAppointment> previousAppointment = null;
    @SerializedName("nextAppointment")
    @Expose
    private List<NextAppointment> nextAppointment = null;

    public List<PreviousAppointment> getPreviousAppointment() {
        return previousAppointment;
    }

    public void setPreviousAppointment(List<PreviousAppointment> previousAppointment) {
        this.previousAppointment = previousAppointment;
    }

    public List<NextAppointment> getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(List<NextAppointment> nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

}
