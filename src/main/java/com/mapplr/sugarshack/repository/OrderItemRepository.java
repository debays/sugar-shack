package com.mapplr.sugarshack.repository;

import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByIdAndDeletedFalse(Long id);
}
