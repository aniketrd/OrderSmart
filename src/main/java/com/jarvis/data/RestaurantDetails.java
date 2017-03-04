package com.jarvis.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Aniket on 26-02-2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RestaurantDetails {

    private Integer restaurantId;
    private String restaurantName;
    private String owerName;
    private String address;
    private String mobileNo;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String subscribitonEnd;
    private String rootUserName;
    private String rootPassword;
}
