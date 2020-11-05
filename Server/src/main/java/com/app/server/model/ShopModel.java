package com.app.server.model;

public class ShopModel {
    public int id;
    public int business_id;
    public String name;
    public String description;
    public String image;
    public double price;



    @Override
    public String toString() {
        return "ShopModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", business_id='" + business_id + '\'' +
                '}';
    }
}
