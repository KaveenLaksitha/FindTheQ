package com.example.findtheq.models;

public class StockModel {
    private String Diesel;
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
