package com.mapplr.sugarshack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderLineDto {
    private Long id;

    private String productId;

    private int qty;
}
