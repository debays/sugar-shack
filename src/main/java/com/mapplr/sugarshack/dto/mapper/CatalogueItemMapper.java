package com.mapplr.sugarshack.dto.mapper;

import com.mapplr.sugarshack.dto.CatalogueItemDto;
import com.mapplr.sugarshack.model.MapleSyrup;

import java.util.ArrayList;
import java.util.List;

public class CatalogueItemMapper {

    public static CatalogueItemDto convertToCatalogueItemDto(MapleSyrup syrup) {
        CatalogueItemDto catalogueItemDto = new CatalogueItemDto();
        catalogueItemDto.setId(syrup.getId().toString());
        catalogueItemDto.setName(syrup.getName());
        catalogueItemDto.setPrice(syrup.getPrice());
        catalogueItemDto.setImage(syrup.getImage());
        return catalogueItemDto;
    }

    public static List<CatalogueItemDto> convertToCatalogueItemDto(List<MapleSyrup> syrup) {
        List<CatalogueItemDto> catalogueItemDto = new ArrayList<>();
        for (MapleSyrup mapleSyrup : syrup) {
            catalogueItemDto.add(convertToCatalogueItemDto(mapleSyrup));
        }
        return catalogueItemDto;
    }

}
