package com.mapplr.sugarshack.service;

import com.mapplr.sugarshack.dto.OrderDto;
import com.mapplr.sugarshack.dto.OrderItemDto;
import com.mapplr.sugarshack.dto.OrderLineDto;
import com.mapplr.sugarshack.dto.OrderValidationResponseDto;
import com.mapplr.sugarshack.dto.mapper.OrderMapper;
import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.model.Order;
import com.mapplr.sugarshack.model.OrderItem;
import com.mapplr.sugarshack.model.OrderStatus;
import com.mapplr.sugarshack.repository.OrderItemRepository;
import com.mapplr.sugarshack.repository.OrderRepository;
import com.mapplr.sugarshack.repository.SyrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SyrupRepository syrupRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public OrderDto validateOrder(OrderDto orderDto) {
        Order order = createNewOrder(orderDto);

        List<OrderItem> orderItems = new ArrayList<>();
        orderDto.getOrderItems()
                .forEach(orderItemDto -> createOrderItem(order, orderItems, orderItemDto));
        order.setOrderItems(orderItems);

        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        return OrderMapper.entityToDto(order);
    }


    @Transactional
    public OrderValidationResponseDto placeOrder(List<OrderLineDto> orderLineDtoList, Principal principal) {
        Order order = new Order();
        order.setOrderDate(ZonedDateTime.now());
        order.setCustomerName(principal.getName());

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (OrderLineDto orderLineDto : orderLineDtoList) {
            MapleSyrup mapleSyrup = syrupRepository.findById(Long.parseLong(orderLineDto.getProductId()))
                    .orElseThrow(() -> new RuntimeException("Maple Syrup not found with id: " + orderLineDto.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setMapleSyrup(mapleSyrup);
            orderItem.setQuantity(orderLineDto.getQty());
            orderItem.setOrder(order);

            orderItems.add(orderItem);
            totalPrice += mapleSyrup.getPrice() * orderLineDto.getQty();
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        OrderValidationResponseDto responseDto = new OrderValidationResponseDto();
        responseDto.setOrderValid(true);
        responseDto.setErrors(null);

        return responseDto;
    }

    private Order createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomerAddress(orderDto.getCustomerAddress());
        order.setCustomerMail(orderDto.getCustomerMail());
        order.setCustomerName(orderDto.getCustomerName());
        order.setCustomerPhone(orderDto.getCustomerPhone());
        order.setOrderDate(orderDto.getOrderDate());
        orderRepository.save(order);
        return order;
    }

    private void createOrderItem(Order order, List<OrderItem> orderItems, OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setMapleSyrup(syrupRepository.findByIdAndDeletedFalse(orderItemDto.getSyrupId())
                .orElseThrow(() -> new ResourceNotFoundException("Maple syrup not found")));
        orderItem.setOrder(order);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItemRepository.save(orderItem);
        orderItems.add(orderItem);
    }
}