package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.SyrupType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapleSyrupDto {
    private String description;
    private String id;
    private String image;
    private String name;
    private double price;
    private int stock;
    private SyrupType type;
}
