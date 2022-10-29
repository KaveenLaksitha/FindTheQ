package com.example.findtheq.DBHandler;

public class DbModel {
    private  int id;
    private String email;
    private String vehicleType;
    private String joinedStationID;

    public DbModel( String email, String vehicleType, String joinedStationID) {
        this.email = email;
        this.vehicleType = vehicleType;
        this.joinedStationID = joinedStationID;
    }

    public DbModel(String email, String vehicleType) {
        this.email = email;
        this.vehicleType = vehicleType;
        this.joinedStationID = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getJoinedStationID() {
        return joinedStationID;
    }

    public void setJoinedStationID(String joinedStationID) {
        this.joinedStationID = joinedStationID;
    }
}
