package com.example.findtheq.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockModel {
    @SerializedName("diesel")
    @Expose
    private String Diesel;
    @SerializedName("petrol")
    @Expose
    private String Petrol;

    public StockModel(String diesel, String petrol) {
        Diesel = diesel;
        Petrol = petrol;
    }

    public String getDiesel() {
        return Diesel;
    }

    public String getPetrol() {
        return Petrol;
    }

    public void setDiesel(String diesel) {
        Diesel = diesel;
    }

    public void setPetrol(String petrol) {
        Petrol = petrol;
    }
}
