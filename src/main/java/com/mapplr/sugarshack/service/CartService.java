package com.mapplr.sugarshack.service;


import com.mapplr.sugarshack.dto.CartDto;
import com.mapplr.sugarshack.dto.CartItemDto;
import com.mapplr.sugarshack.dto.CartMapper;
import com.mapplr.sugarshack.model.Cart;
import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.repository.CartItemRepository;
import com.mapplr.sugarshack.repository.CartRepository;
import com.mapplr.sugarshack.repository.SyrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private SyrupRepository syrupRepository;

    public CartDto getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return CartMapper.entityToDto(cart);
    }

    @Transactional
    public CartDto addItemToCart(CartItemDto itemDTO) {
        MapleSyrup mapleSyrup = syrupRepository.findByIdAndDeletedFalse(itemDTO.getMapleSyrupDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Maple syrup not found"));

        CartItem cartItem = cartItemRepository.findByIdAndDeletedFalse(itemDTO.getId())
                .orElseGet(() -> getNewCartItem(itemDTO, mapleSyrup));

        Cart cart = cartRepository.findByIdAndDeletedFalse(itemDTO.getCartDto().getId())
                .orElseGet(this::getNewCart);

        updateCartForAddItem(cartItem, cart);
        cartRepository.save(cart);

        return CartMapper.entityToDto(cart);
    }

    @Transactional
    public CartDto removeProductFromCart(CartItemDto itemDTO) {
        CartItem cartItem = cartItemRepository.findByIdAndDeletedFalse(itemDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        Cart cart = cartItem.getCart();
        cart.getItems().remove(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getPrice() * itemDTO.getQuantity()));
        cartRepository.save(cart);

        cartItemRepository.delete(cartItem);

        return CartMapper.entityToDto(cart);
    }

    @Transactional
    public CartDto deleteCart(Long cartId){
        Cart cart = cartRepository.findByIdAndDeletedFalse(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        for (CartItem item : cart.getItems()) {
            cartItemRepository.delete(item);
        }

        cartRepository.delete(cart);
        return CartMapper.entityToDto(cart);
    }

    private void updateCartForAddItem(CartItem cartItem, Cart cart) {
        Double actualPrice = cart.getTotalPrice();
        actualPrice += cartItem.getPrice();
        cart.setTotalPrice(actualPrice);
        List<CartItem> items = cart.getItems();
        items.add(cartItem);
        cart.setItems(items);
    }

    private CartItem getNewCartItem(CartItemDto itemDTO, MapleSyrup mapleSyrup) {
        CartItem newCartItem = new CartItem();
        newCartItem.setQuantity(itemDTO.getQuantity());
        newCartItem.setMapleSyrup(mapleSyrup);
        newCartItem.setPrice(itemDTO.getPrice() * itemDTO.getQuantity());
        cartItemRepository.save(newCartItem);
        return newCartItem;
    }

    private Cart getNewCart() {
        Cart newCart = new Cart();
        newCart.setCreatedAt(ZonedDateTime.now());
        newCart.setItems(new ArrayList<>());
        newCart.setTotalPrice(0D);
        cartRepository.save(newCart);
        return newCart;
    }
}
