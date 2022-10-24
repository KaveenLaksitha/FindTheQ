package com.example.findtheq.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("customername")
    @Expose
    private String customername;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("vehicleid")
    @Expose
    private String vehicleid;

    @SerializedName("nic")
    @Expose
    private String nic;

    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;

    @SerializedName("vehicletype")
    @Expose
    private String vehicletype;

    @SerializedName("password")
    @Expose
    private String password;

    public User(String customername, String email, String vehicleid, String nic, String phonenumber, String vehicletype, String password) {
        this.customername = customername;
        this.email = email;
        this.vehicleid = vehicleid;
        this.nic = nic;
        this.phonenumber = phonenumber;
        this.vehicletype = vehicletype;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
