package com.mapplr.sugarshack.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime orderDate;

    private Boolean deleted;

    // TODO : Could be an entity "User" or "Customer"
    private String customerName;
    private String customerAddress;
    private String customerMail;
    private String customerPhone;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    private OrderStatus orderStatus;

    private double totalPrice;
}
