package com.mapplr.sugarshack.service;

import com.mapplr.sugarshack.dto.OrderDto;
import com.mapplr.sugarshack.dto.OrderItemDto;
import com.mapplr.sugarshack.dto.mapper.OrderMapper;
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