package com.wipro.ecs.entity;

public class User {

    private String userId;
    private String name;
    private String contactNumber;

    //adding constructor
    public User(String userid, String name, String contactNumber){
        this.userId = userid;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
