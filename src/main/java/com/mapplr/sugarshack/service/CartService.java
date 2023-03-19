package com.mapplr.sugarshack.service;


import com.mapplr.sugarshack.dto.CartDto;
import com.mapplr.sugarshack.dto.OrderLineDto;
import com.mapplr.sugarshack.dto.mapper.CartItemMapper;
import com.mapplr.sugarshack.dto.mapper.CartMapper;
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

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private SyrupRepository syrupRepository;

    public List<OrderLineDto> getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return CartItemMapper.listEntityToListDto(cart.getItems());
    }

    @Transactional
    public CartDto addItemToCart(String productId, Principal principal) {
        // find the MapleSyrup entity for the given product ID
        MapleSyrup mapleSyrup = syrupRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new EntityNotFoundException("MapleSyrup not found with ID: " + productId));

        // find the Cart entity for the user (or create a new one if it doesn't exist yet)
        Cart cart = cartRepository.findActiveCart(principal.getName())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCreatedAt(ZonedDateTime.now());
                    return cartRepository.save(newCart);
                });

        // find the CartItem entity for the MapleSyrup and Cart (or create a new one if it doesn't exist yet)
        CartItem cartItem = cartItemRepository.findByCartAndMapleSyrupAndDeletedFalse(cart, mapleSyrup)
                .orElseGet(() -> {
                    CartItem newCartItem = new CartItem();
                    newCartItem.setCart(cart);
                    newCartItem.setMapleSyrup(mapleSyrup);
                    newCartItem.setQuantity(0);
                    newCartItem.setPrice(mapleSyrup.getPrice());
                    return cartItemRepository.save(newCartItem);
                });

        // increment the quantity and price of the CartItem
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setPrice(cartItem.getQuantity() * mapleSyrup.getPrice());

        // update the total price of the Cart
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());

        // save the updated Cart and CartItem entities
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return CartMapper.entityToDto(cart);
    }

    @Transactional
    public CartDto removeFromCart(String productId, Principal principal) {
        // find the MapleSyrup entity for the given product ID
        MapleSyrup mapleSyrup = syrupRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new EntityNotFoundException("MapleSyrup not found with ID: " + productId));

        // find the Cart entity for the user
        Cart cart = cartRepository.findActiveCart(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for active user"));

        // find the CartItem entity for the MapleSyrup and Cart
        CartItem cartItem = cartItemRepository.findByCartAndMapleSyrupAndDeletedFalse(cart, mapleSyrup)
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found for Cart and MapleSyrup"));

        // decrement the quantity and price of the CartItem
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        cartItem.setPrice(cartItem.getQuantity() * mapleSyrup.getPrice());

        // if the quantity is now zero, remove the CartItem from the Cart
        if (cartItem.getQuantity() == 0) {
            cart.getItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        }

        // update the total price of the Cart
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());

        // save the updated Cart and CartItem entities
        cartRepository.save(cart);
        return CartMapper.entityToDto(cart);
    }

    @Transactional
    public CartDto changeQuantity(String productId, int newQty) {
        // Find the CartItem by productId
        CartItem cartItem = cartRepository.findCartItemByProductId(Long.valueOf(productId))
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found for productId: " + productId));

        // Update the CartItem quantity and price
        cartItem.setQuantity(newQty);
        cartItem.setPrice(cartItem.getMapleSyrup().getPrice() * newQty);

        // Recalculate and update the Cart total price
        Cart cart = cartItem.getCart();
        List<CartItem> cartItems = cart.getItems();
        Double totalPrice = cartItems.stream()
                .mapToDouble(ci -> ci.getPrice())
                .sum();
        cart.setTotalPrice(totalPrice);

        // Save the updated entities
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return CartMapper.entityToDto(cart);
    }


    @Transactional
    public CartDto deleteCart(Long cartId) {
        Cart cart = cartRepository.findByIdAndDeletedFalse(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        for (CartItem item : cart.getItems()) {
            cartItemRepository.delete(item);
        }

        cartRepository.delete(cart);
        return CartMapper.entityToDto(cart);
    }
}
