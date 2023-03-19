package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.Order;

public class OrderMapper {

    public static OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerAddress(order.getCustomerAddress());
        orderDto.setCustomerMail(order.getCustomerMail());
        orderDto.setCustomerName(order.getCustomerName());
        orderDto.setCustomerPhone(order.getCustomerPhone());
        orderDto.setId(order.getId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderItems(OrderItemMapper.listEntitiesToDtos(order.getOrderItems()));
        orderDto.setOrderStatus(order.getOrderStatus());
        return orderDto;
    }
}
