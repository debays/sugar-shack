package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.SyrupType;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MapleSyrupDto {
    private Long id;
    private SyrupType type;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String image;
}
