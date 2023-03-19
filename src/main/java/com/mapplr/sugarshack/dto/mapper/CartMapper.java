package com.mapplr.sugarshack.dto.mapper;

import com.mapplr.sugarshack.dto.CartDto;
import com.mapplr.sugarshack.model.Cart;

public class CartMapper {

    public static CartDto entityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setItems(CartItemMapper.listEntityToListDto(cart.getItems()));
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setCreatedAt(cart.getCreatedAt());
        return cartDto;
    }
}
