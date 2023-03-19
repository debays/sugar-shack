package com.mapplr.sugarshack.controller;

import com.mapplr.sugarshack.dto.OrderDto;
import com.mapplr.sugarshack.dto.OrderLineDto;
import com.mapplr.sugarshack.dto.OrderValidationResponseDto;
import com.mapplr.sugarshack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

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

    @PostMapping("/place")
    public ResponseEntity<OrderValidationResponseDto> placeOrder(@RequestBody List<OrderLineDto> orderLineDtoList, Principal principal) {
        OrderValidationResponseDto responseDto = orderService.placeOrder(orderLineDtoList, principal);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
