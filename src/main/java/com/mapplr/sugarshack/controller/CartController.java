package com.mapplr.sugarshack.controller;

import com.mapplr.sugarshack.dto.CartDto;
import com.mapplr.sugarshack.dto.CartItemDto;
import com.mapplr.sugarshack.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PostMapping
    public CartDto addItemToCart(@RequestBody CartItemDto item) {
        return cartService.addItemToCart(item);
    }

    @PostMapping
    public CartDto removeProductFromCart(@RequestBody CartItemDto item) {
        return cartService.removeProductFromCart(item);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
