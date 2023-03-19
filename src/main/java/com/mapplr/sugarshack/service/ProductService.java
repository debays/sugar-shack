package com.mapplr.sugarshack.service;

import com.mapplr.sugarshack.dto.MapleSyrupDto;
import com.mapplr.sugarshack.dto.mapper.MapleSyrupMapper;
import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.repository.SyrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private SyrupRepository syrupRepository;

    public MapleSyrupDto getProductInfo(String productId) {
        Optional<MapleSyrup> mapleSyrup = syrupRepository.findByIdAndDeletedFalse(Long.valueOf(productId));
        if (mapleSyrup.isPresent()) {
            MapleSyrup product = mapleSyrup.get();
            return MapleSyrupMapper.entityToDto(product);
        } else {
            return null;
        }
    }
}