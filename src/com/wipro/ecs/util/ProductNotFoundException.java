package com.wipro.ecs.util;

public class ProductNotFoundException extends Exception {

    public  String toString(){

        return "Invalid productId. Product not found !";
    }
}
