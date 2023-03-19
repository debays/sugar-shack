package com.mapplr.sugarshack.repository;

import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.MapleSyrup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByIdAndDeletedFalse(Long id);
}
