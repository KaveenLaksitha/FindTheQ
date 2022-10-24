package com.example.findtheq.models;

public class QueueModel {
    private int car;
    private int bike;
    private int tuk;
    private int van;
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
}