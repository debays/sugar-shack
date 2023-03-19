package com.mapplr.sugarshack.dto.mapper;

import com.mapplr.sugarshack.dto.OrderLineDto;
import com.mapplr.sugarshack.model.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartItemMapper {

    public static OrderLineDto entityToDto(CartItem cartItem){
        OrderLineDto orderLineDto = new OrderLineDto();
        orderLineDto.setId(cartItem.getId());
        return orderLineDto;
    }

    public static List<OrderLineDto> listEntityToListDto(List<CartItem> cartItemList){
        return cartItemList.stream().map(CartItemMapper::entityToDto).collect(Collectors.toList());
    }
}
