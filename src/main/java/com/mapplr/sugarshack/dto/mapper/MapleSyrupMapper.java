package com.mapplr.sugarshack.dto.mapper;

import com.mapplr.sugarshack.dto.MapleSyrupDto;
import com.mapplr.sugarshack.model.MapleSyrup;

import java.util.ArrayList;
import java.util.List;

public class MapleSyrupMapper {

    public MapleSyrup dtoToEntity(MapleSyrupDto mapleSyrupDto){
        return null;
    }

    public static MapleSyrupDto entityToDto(MapleSyrup mapleSyrup){
        MapleSyrupDto mapleSyrupDTO = new MapleSyrupDto();
        mapleSyrupDTO.setId(mapleSyrup.getId().toString());
        mapleSyrupDTO.setType(mapleSyrup.getType());
        mapleSyrupDTO.setName(mapleSyrup.getName());
        mapleSyrupDTO.setPrice(mapleSyrup.getPrice());
        mapleSyrupDTO.setStock(mapleSyrup.getStock());
        return mapleSyrupDTO;
    }

    public static List<MapleSyrupDto> listToDto(List<MapleSyrup> mapleSyrup){
        List<MapleSyrupDto> mapleSyrupDTOs = new ArrayList<>();
        for (MapleSyrup syrup : mapleSyrup) {
            mapleSyrupDTOs.add(entityToDto(syrup));
        }
        return mapleSyrupDTOs;
    }

}
