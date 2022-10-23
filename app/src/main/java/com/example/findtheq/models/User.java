package com.example.findtheq.models;

public class User {

    private String customername;
    private String vehicleid;
    private String nic;
    private String phonenumber;
    private String vehicletype;
    private String fueltype;
    private String password;

    public User(String customername, String vehicleid, String nic, String phonenumber, String vehicletype,  String fueltype , String password) {
        this.customername = customername;
        this.vehicleid = vehicleid;
        this.nic = nic;
        this.phonenumber = phonenumber;
        this.vehicletype = vehicletype;
        this.fueltype = fueltype;
        this.password = password;
    }

    public String getCustomername() {
        return customername;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public String getNic() {
        return nic;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public String getFueltype() {
        return fueltype;
    }
}
