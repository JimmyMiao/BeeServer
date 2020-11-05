package com.app.server.model;

public class BusinessModel {
    public int id;
    public String name;
    public int monthSell;
    public String image;
    public double startPrice;
    public int averageTime;



    @Override
    public String toString() {
        return "BusinessModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", startPrice='" + startPrice + '\'' +
                ", AverageTime='" + averageTime + '\'' +
                ", monthSell='" + monthSell + '\'' +
                '}';
    }
}
