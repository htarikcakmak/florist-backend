package com.cicekci.api.repository;

import com.cicekci.api.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByFlowerIdOrderByCreatedAtDesc(Long flowerId);
    List<ReviewEntity> findByStoreIdOrderByCreatedAtDesc(Long storeId);
    List<ReviewEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
}
