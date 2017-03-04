package com.jarvis.data;

import lombok.Data;

/**
 * Created by Aniket on 01-03-2017.
 */
@Data
public class MenuItem {

    private Integer menuItemId;
    private Integer restaurantId;
    private String itemName;
    private Float price;
    private String description;
    private String category;
    private String subCategory;
    private Boolean isVeg;
    private String photoPath;

}
