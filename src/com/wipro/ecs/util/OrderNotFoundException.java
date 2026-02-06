package com.wipro.ecs.util;

public class OrderNotFoundException extends Exception {

    public  String toString(){

        return "Please check your order. Order not found !";
    }
}
