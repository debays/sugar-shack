package com.mapplr.sugarshack.dto;

import com.mapplr.sugarshack.model.MapleSyrup;

public class MapleSyrupMapper {

    public MapleSyrup dtoToEntity(MapleSyrupDto mapleSyrupDto){
        return null;
    }

    public static MapleSyrupDto entityToDto(MapleSyrup mapleSyrup){
        MapleSyrupDto mapleSyrupDTO = new MapleSyrupDto();
        mapleSyrupDTO.setId(mapleSyrup.getId());
        mapleSyrupDTO.setType(mapleSyrup.getType());
        mapleSyrupDTO.setName(mapleSyrup.getName());
        mapleSyrupDTO.setPrice(mapleSyrup.getPrice());
        mapleSyrupDTO.setStock(mapleSyrup.getStock());
        return mapleSyrupDTO;
    }
}
