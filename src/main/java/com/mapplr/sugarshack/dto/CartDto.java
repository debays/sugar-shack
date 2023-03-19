package com.mapplr.sugarshack.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter@Setter
public class CartDto {
    private Long id;

    private List<OrderLineDto> items;

    private Double totalPrice;

    private ZonedDateTime createdAt;
}
