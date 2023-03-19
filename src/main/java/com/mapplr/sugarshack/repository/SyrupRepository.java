package com.mapplr.sugarshack.repository;

import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.MapleSyrup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SyrupRepository extends JpaRepository<MapleSyrup, Long> {
    Optional<MapleSyrup> findByIdAndDeletedFalse(Long id);
}
