package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.OrderItem;
import com.mapplr.sugarshack.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private ZonedDateTime orderDate;
    private String customerName;
    private String customerAddress;
    private String customerMail;
    private String customerPhone;
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems;
}
