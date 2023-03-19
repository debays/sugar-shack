package com.mapplr.sugarshack.datainitialization;

import com.mapplr.sugarshack.model.Cart;
import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.model.SyrupType;
import com.mapplr.sugarshack.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class DataInitialization implements CommandLineRunner {

    private final SyrupRepository syrupRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public DataInitialization(SyrupRepository syrupRepository, CartRepository cartRepository,
                           CartItemRepository cartItemRepository, OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository) {
        this.syrupRepository = syrupRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void run(String... args) {
        // Create some sample Maple Syrup products
        MapleSyrup mapleSyrup1 = new MapleSyrup();
        mapleSyrup1.setType(SyrupType.AMBER);
        mapleSyrup1.setName("Pure Amber Maple Syrup");
        mapleSyrup1.setDescription("Grade A, dark color and robust taste");
        mapleSyrup1.setPrice(15.99);
        mapleSyrup1.setStock(100);
        mapleSyrup1.setImage("https://example.com/maple_syrup1.jpg");
        syrupRepository.save(mapleSyrup1);

        MapleSyrup mapleSyrup2 = new MapleSyrup();
        mapleSyrup2.setType(SyrupType.DARK);
        mapleSyrup2.setName("Organic Dark Maple Syrup");
        mapleSyrup2.setDescription("Grade A, dark color and bold taste");
        mapleSyrup2.setPrice(12.99);
        mapleSyrup2.setStock(50);
        mapleSyrup2.setImage("https://example.com/maple_syrup2.jpg");
        syrupRepository.save(mapleSyrup2);

        MapleSyrup mapleSyrup3 = new MapleSyrup();
        mapleSyrup3.setType(SyrupType.CLEAR);
        mapleSyrup3.setName("Golden Delicate Maple Syrup");
        mapleSyrup3.setDescription("Grade A, light color and delicate taste");
        mapleSyrup3.setPrice(18.99);
        mapleSyrup3.setStock(75);
        mapleSyrup3.setImage("https://example.com/maple_syrup3.jpg");
        syrupRepository.save(mapleSyrup3);

        // Create a sample cart for a user
        Cart cart = new Cart();
        cart.setUsername("john_doe");
        cart.setCreatedAt(ZonedDateTime.now());
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        // Add some items to the cart
        CartItem cartItem1 = new CartItem();
        cartItem1.setMapleSyrup(mapleSyrup1);
        cartItem1.setQuantity(2);
        cartItem1.setPrice(mapleSyrup1.getPrice() * cartItem1.getQuantity());
        cartItem1.setCart(cart);
        cart.getItems().add(cartItem1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setMapleSyrup(mapleSyrup2);
        cartItem2.setQuantity(1);
        cartItem2.setPrice(mapleSyrup2.getPrice() * cartItem2.getQuantity());
        cartItem2.setCart(cart);
        cart.getItems().add(cartItem2);

        cart.setTotalPrice(cartItem1.getPrice() + cartItem2.getPrice());
        cartRepository.save(cart);
    }
}