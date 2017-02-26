package com.jarvis.data;

import java.sql.Timestamp;

/**
 * Created by Aniket on 26-02-2017.
 */

public class RestaurantDetails {

    private Integer RestaurantId;
    private String RestaurantName;
    private String OwerName;
    private String Address;
    private String MobileNo;
    private Timestamp CreatedTime;
    private Timestamp UpdatedTime;
    private Timestamp SubscribitonEnd;
    private String RootUserName;

    public Integer getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getOwerName() {
        return OwerName;
    }

    public void setOwerName(String owerName) {
        OwerName = owerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public Timestamp getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        CreatedTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return UpdatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        UpdatedTime = updatedTime;
    }

    public Timestamp getSubscribitonEnd() {
        return SubscribitonEnd;
    }

    public void setSubscribitonEnd(Timestamp subscribitonEnd) {
        SubscribitonEnd = subscribitonEnd;
    }

    public String getRootUserName() {
        return RootUserName;
    }

    public void setRootUserName(String rootUserName) {
        RootUserName = rootUserName;
    }
}
