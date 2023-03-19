package com.mapplr.sugarshack.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "maple_syrup_id")
    private MapleSyrup mapleSyrup;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}