package com.wipro.ecs.util;

public class InvalidUserException extends Exception {

    public  String  toString(){

        return "User ID does not exist, Please Check !";
    }

}
