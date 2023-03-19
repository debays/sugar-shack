package com.mapplr.sugarshack.controller;

import com.mapplr.sugarshack.dto.CartDto;
import com.mapplr.sugarshack.dto.OrderLineDto;
import com.mapplr.sugarshack.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public List<OrderLineDto> getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PutMapping("/addToCart")
    public ResponseEntity<CartDto> addToCart(@RequestParam("productId") String productId, Principal principal) {
        return new ResponseEntity<>(cartService.addItemToCart(productId, principal), HttpStatus.ACCEPTED);
    }

    @DeleteMapping()
    public ResponseEntity<CartDto> removeFromCart(@RequestParam String productId, Principal principal) {
        return new ResponseEntity<>(cartService.removeFromCart(productId, principal), HttpStatus.ACCEPTED);
    }

    @PostMapping("/changeQty")
    public ResponseEntity<CartDto> changeQuantity(@RequestParam String productId, @RequestParam int newQty) {
        return new ResponseEntity<>(cartService.changeQuantity(productId, newQty), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
