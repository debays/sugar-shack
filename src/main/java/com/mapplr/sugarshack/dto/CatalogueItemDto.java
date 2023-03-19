package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.SyrupType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogueItemDto {
    private String id;
    private String image;
    private int maxQty;
    private String name;
    private double price;
    private SyrupType type;
}

