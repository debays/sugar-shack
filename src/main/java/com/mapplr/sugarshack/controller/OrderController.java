package com.mapplr.sugarshack.controller;

import com.mapplr.sugarshack.dto.OrderDto;
import com.mapplr.sugarshack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> validateOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.validateOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
