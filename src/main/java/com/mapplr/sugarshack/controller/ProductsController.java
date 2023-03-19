package com.mapplr.sugarshack.controller;

import com.mapplr.sugarshack.dto.CatalogueItemDto;
import com.mapplr.sugarshack.dto.MapleSyrupDto;
import com.mapplr.sugarshack.dto.mapper.CatalogueItemMapper;
import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.model.SyrupType;
import com.mapplr.sugarshack.repository.SyrupRepository;
import com.mapplr.sugarshack.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private SyrupRepository syrupRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<CatalogueItemDto> getCatalogue(@RequestParam(required = false) SyrupType type) {
        List<MapleSyrup> syrups;

        // TODO : use a criteria and create a service
        if (type != null) {
            syrups = syrupRepository.findByTypeAndDeletedFalse(type).orElseThrow(() -> new ResourceNotFoundException("Maple Syrup product not found for type" + type));
        } else {
            syrups = syrupRepository.findAllByDeletedFalse();
        }
        return CatalogueItemMapper.convertToCatalogueItemDto(syrups);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<MapleSyrupDto> getProductInfo(@PathVariable String productId) {
        MapleSyrupDto mapleSyrup = productService.getProductInfo(productId);
        if (mapleSyrup != null) {
            return ResponseEntity.ok(mapleSyrup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}