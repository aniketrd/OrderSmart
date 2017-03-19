package com.jarvis.data;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Aniket on 04-03-2017.
 */
@Data
public class Order {
    private Integer orderId;
    private String orderType;
    private String orderStatus;
    private Boolean orderStatusFlag;
    private Integer guestCount;
    private Integer customerId;
    private Integer tableId;
    private Integer restaurantId;
    private Timestamp createdTime;
    private String createdBy;
}
