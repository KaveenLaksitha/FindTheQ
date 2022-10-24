package com.example.findtheq.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueueModel {
    @SerializedName("Car")
    @Expose
    private int car;
    @SerializedName("Bike")
    @Expose
    private int bike;
    @SerializedName("Tuk")
    @Expose
    private int tuk;
    @SerializedName("Van")
    @Expose
    private int van;
    @SerializedName("Bus")
    @Expose
    private int bus;

    public QueueModel(int car, int bike, int tuk, int van, int bus) {
        this.car = car;
        this.bike = bike;
        this.tuk = tuk;
        this.van = van;
        this.bus = bus;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getBike() {
        return bike;
    }

    public void setBike(int bike) {
        this.bike = bike;
    }

    public int getTuk() {
        return tuk;
    }

    public void setTuk(int tuk) {
        this.tuk = tuk;
    }

    public int getVan() {
        return van;
    }

    public void setVan(int van) {
        this.van = van;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }
}

