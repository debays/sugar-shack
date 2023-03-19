package com.mapplr.sugarshack.repository;

import com.mapplr.sugarshack.model.CartItem;
import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.model.SyrupType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SyrupRepository extends JpaRepository<MapleSyrup, Long> {
    Optional<MapleSyrup> findByIdAndDeletedFalse(Long id);

    Optional<List<MapleSyrup>> findByTypeAndDeletedFalse(SyrupType type);

    List<MapleSyrup> findAllByDeletedFalse();
}
