package com.poligram.model;


public enum UserProfileType {
    USER("USER"),
    ADMIN("ADMIN");


    String userProfileType;

    UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }
}
