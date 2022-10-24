package com.example.findtheq;

import com.example.findtheq.models.Station;
import com.google.gson.annotations.SerializedName;

public class ResponseJSON {
//    @SerializedName("")
    private Station[] stationsArray;

    public Station[] getStationsArray() {
        return stationsArray;
    }

    public void setStationsArray(Station[] stationsArray) {
        this.stationsArray = stationsArray;
    }
}
