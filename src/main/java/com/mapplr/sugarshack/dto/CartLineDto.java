package com.mapplr.sugarshack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartLineDto {
    private String image;
    private String name;
    private double price;
    private String productId;
    private int qty;
}
