package com.mapplr.sugarshack.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Entity
@Table(name = "maple_syrup")
public class MapleSyrup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted;

    private SyrupType type;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String image;
}
