package com.mapplr.sugarshack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private Long syrupId;
    private Integer quantity;
    private double price;
}
