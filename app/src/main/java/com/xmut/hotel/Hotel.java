package com.xmut.hotel;

public class Hotel {
    private String name;
    private double price;
    private int imageId;

    public Hotel(String name, double price, int imageId){
        this.name = name;
        this.price = price;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageId() {
        return imageId;
    }
}
