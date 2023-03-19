package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartItemMapper {

    public static CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setMapleSyrupDto(MapleSyrupMapper.entityToDto(cartItem.getMapleSyrup()));
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setCartDto(CartMapper.entityToDto(cartItem.getCart()));
        return cartItemDto;
    }

    public static List<CartItemDto> listEntityToListDto(List<CartItem> cartItemList){
        return cartItemList.stream().map(CartItemMapper::entityToDto).collect(Collectors.toList());
    }
}
