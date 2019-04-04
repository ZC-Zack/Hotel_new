package com.xmut.hotel;

public class Room {
    private String name;
    private double price;
    private int imageId;


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageId(int imageId) {
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
