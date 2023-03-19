package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.Order;
import com.mapplr.sugarshack.model.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {

    public static OrderItemDto entityToDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setPrice(orderItem.getMapleSyrup().getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setSyrupId(orderItem.getMapleSyrup().getId());
        return orderItemDto;
    }

    public static List<OrderItemDto> listEntitiesToDtos(List<OrderItem> orderItems){
        List<OrderItemDto> orderItemDtos = orderItems
                .stream()
                .map(OrderItemMapper::entityToDto)
                .collect(Collectors.toList());
        return orderItemDtos;
    }
}
