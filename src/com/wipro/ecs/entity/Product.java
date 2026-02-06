package com.wipro.ecs.entity;

public class Product {

    private String productId;
    private String productName;
    private double price;
    private int stock;

    public Product(String productId, String productName, double price, int stock){

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId +
                ", Name: " + productName +
                ", Price: " + price +
                ", Stock: " + stock;
    }

}
