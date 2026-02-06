package com.wipro.ecs.util;

public class OrderOperationException extends Exception {

    public  String toString(){

        return "Invalid to proceed. Check the items, cart, order of the products.";
    }
}
