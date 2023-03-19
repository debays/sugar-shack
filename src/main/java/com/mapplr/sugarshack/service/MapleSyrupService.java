package com.mapplr.sugarshack.service;

import com.mapplr.sugarshack.dto.MapleSyrupDto;
import com.mapplr.sugarshack.dto.mapper.MapleSyrupMapper;
import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.model.SyrupType;
import com.mapplr.sugarshack.repository.SyrupRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapleSyrupService {

    private final SyrupRepository mapleSyrupRepository;

    public MapleSyrupService(SyrupRepository mapleSyrupRepository) {
        this.mapleSyrupRepository = mapleSyrupRepository;
    }

    public List<MapleSyrupDto> getAllMapleSyrups(String type) {
        List<MapleSyrup> mapleSyrups = type == null ?
                mapleSyrupRepository.findAll() :
                mapleSyrupRepository.findByTypeDeletedFalse(SyrupType.valueOf(type))
                        .orElseThrow(() -> new ResourceNotFoundException("Maple Syrup product not found for type" + type));

        return mapleSyrups.stream()
                .map(MapleSyrupMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public MapleSyrupDto getMapleSyrupProductById(Long id) {
        MapleSyrup syrup = mapleSyrupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maple Syrup Product not found with id " + id));

        return MapleSyrupMapper.entityToDto(syrup);
    }
}
