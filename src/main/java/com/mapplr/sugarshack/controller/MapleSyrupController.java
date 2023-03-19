package com.mapplr.sugarshack.controller;

import com.mapplr.sugarshack.dto.MapleSyrupDto;
import com.mapplr.sugarshack.service.MapleSyrupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maple-syrup")
public class MapleSyrupController {

    private final MapleSyrupService mapleSyrupService;

    public MapleSyrupController(MapleSyrupService mapleSyrupService) {
        this.mapleSyrupService = mapleSyrupService;
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<MapleSyrupDto>> getAllMapleSyrups(@RequestParam(required = false) String type) {
        List<MapleSyrupDto> mapleSyrups = mapleSyrupService.getAllMapleSyrups(type);
        return new ResponseEntity<>(mapleSyrups, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapleSyrupDto> getMapleSyrupProductById(@PathVariable Long id) {
        return ResponseEntity.ok(mapleSyrupService.getMapleSyrupProductById(id));
    }
}

