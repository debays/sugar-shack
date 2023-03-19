package com.mapplr.sugarshack.repository;

import com.mapplr.sugarshack.model.Cart;
import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.MapleSyrup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByIdAndDeletedFalse(Long id);

    @Query("SELECT ci FROM CartItem ci " +
            "JOIN ci.cart c " +
            "WHERE c.deleted = false " +
            "AND ci.deleted = false " +
            "AND ci.mapleSyrup.id = :productId")
    Optional<CartItem> findCartItemByProductId(@Param("productId") Long productId);


    @Query("SELECT c FROM Cart c " +
            "WHERE " +
            "c.username = :userName " +
            "And c.deleted = false")
    Optional<Cart> findActiveCart(@Param("userName") String userName);
}
