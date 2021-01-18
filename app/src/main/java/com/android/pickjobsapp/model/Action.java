package com.android.pickjobsapp.model;
import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("status")
    private String status;
    @SerializedName("action")
    private String action;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
