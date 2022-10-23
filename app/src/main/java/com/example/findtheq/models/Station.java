package com.example.findtheq.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("stationid")
    @Expose
    private String stationid;

    @SerializedName("stationname")
    @Expose
    private String stationname;

    @SerializedName("ownername")
    @Expose
    private String ownername;

    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("arrivaltime")
    @Expose
    private String arrivaltime;

    @SerializedName("finishtime")
    @Expose
    private String finishtime;

    @SerializedName("fueltype")
    @Expose
    private String fueltype;

    public Station(String stationid, String stationname, String ownername, String phonenumber, String address, String arrivaltime, String finishtime, String fueltype) {
        this.stationid = stationid;
        this.stationname = stationname;
        this.ownername = ownername;
        this.phonenumber = phonenumber;
        this.address = address;
        this.arrivaltime = arrivaltime;
        this.finishtime = finishtime;
        this.fueltype = fueltype;
    }

    public Station(String stationid, String stationname, String ownername, String phonenumber, String address) {
        this.stationid = stationid;
        this.stationname = stationname;
        this.ownername = ownername;
        this.phonenumber = phonenumber;
        this.address = address;
    }

    public String getStationid() {
        return stationid;
    }

    public String getStationname() {
        return stationname;
    }

    public String getOwnername() {
        return ownername;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public String getFueltype() {
        return fueltype;
    }
}
