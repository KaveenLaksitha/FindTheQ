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
        this.Diesel = diesel;
        this.Petrol = petrol;
    }

    public String getDiesel() {
        return Diesel;
    }

    public void setDiesel(String diesel) {
        Diesel = diesel;
    }

    public String getPetrol() {
        return Petrol;
    }

    public void setPetrol(String petrol) {
        Petrol = petrol;
    }
}
