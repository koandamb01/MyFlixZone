package com.David.javaProject.models;

public class ProductInfo {
    public String name;
    public double price;
    public int quantity;
    public String imgLink;

    public ProductInfo() {
    }

    public ProductInfo(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductInfo(String name, double price, int quantity, String imgLink) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
