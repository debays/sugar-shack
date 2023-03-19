package com.mapplr.sugarshack.repository;

import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndDeletedFalse(Long id);
}
