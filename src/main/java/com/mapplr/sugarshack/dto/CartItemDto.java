package com.mapplr.sugarshack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CartItemDto {
    private Long id;

    private MapleSyrupDto mapleSyrupDto;

    private Integer quantity;

    private Double price;

    private CartDto cartDto;
}
