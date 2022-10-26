package com.example.findtheq.models;

import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  Station {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

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

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("stock")
    @Expose
    private StockModel stock;

    @SerializedName("queue")
    @Expose
    private QueueModel queue;

    @SerializedName("password")
    @Expose
    private String password;

    public Station(String id, String name, String ownername, String phonenumber, String address, String arrivaltime, String finishtime, String status, StockModel stock, QueueModel queue) {
        this.id = id;
        this.name = name;
        this.ownername = ownername;
        this.phonenumber = phonenumber;
        this.address = address;
        this.arrivaltime = arrivaltime;
        this.finishtime = finishtime;
        this.status = status;
        this.stock = stock;
        this.queue = queue;

    }

    public Station(String id, String name, String ownername, String phonenumber, String address) {
        this.id = id;
        this.name = name;
        this.ownername = ownername;
        this.phonenumber = phonenumber;
        this.address = address;

    }

    public Station(String id, String name, String ownername, String phonenumber, String address, String password) {
        this.id = id;
        this.name = name;
        this.ownername = ownername;
        this.phonenumber = phonenumber;
        this.address = address;
        this.password = password;
    }

    public Station(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Station(String id, StockModel stock) {
        this.id = id;
        this.stock = stock;
    }

    public Station(String id, String arrivaltime, String finishtime) {
        this.id = id;
        this.arrivaltime = arrivaltime;
        this.finishtime = finishtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StockModel getStock() {
        return stock;
    }

    public void setStock(StockModel stock) {
        this.stock = stock;
    }

    public QueueModel getQueue() {
        return queue;
    }

    public void setQueue(QueueModel queue) {
        this.queue = queue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}