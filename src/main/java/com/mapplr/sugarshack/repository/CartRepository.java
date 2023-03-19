package com.mapplr.sugarshack.repository;

import com.mapplr.sugarshack.model.Cart;
import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.MapleSyrup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart, Long> {

    Optional<Cart> findByIdAndDeletedFalse(Long id);

}
