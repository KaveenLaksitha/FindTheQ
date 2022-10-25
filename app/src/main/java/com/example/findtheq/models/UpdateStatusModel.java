package com.example.findtheq.models;

public class UpdateStatusModel {
    String email;
    Boolean status;
    String stationID;
    String type;

    public UpdateStatusModel(String email, Boolean status, String stationID, String type) {
        this.email = email;
        this.status = status;
        this.stationID = stationID;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
