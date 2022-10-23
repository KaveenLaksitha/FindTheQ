package com.example.findtheq.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("customername")
    @Expose
    private String customername;

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

    public User(String customername, String vehicleid, String nic, String phonenumber, String vehicletype, String password) {
        this.customername = customername;
        this.vehicleid = vehicleid;
        this.nic = nic;
        this.phonenumber = phonenumber;
        this.vehicletype = vehicletype;
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


}
