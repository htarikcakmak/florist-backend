package com.cicekci.api.repository;

import com.cicekci.api.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<OrderEntity> findByStoreIdOrderByCreatedAtDesc(Long storeId);
}
