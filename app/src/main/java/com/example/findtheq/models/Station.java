package com.example.findtheq.models;

public class Station {
    private String stationid;
    private String stationname;
    private String ownername;
    private String phonenumber;
    private String address;
    private String arrivaltime;
    private String finishtime;
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
