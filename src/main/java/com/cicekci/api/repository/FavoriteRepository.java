package com.cicekci.api.repository;

import com.cicekci.api.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    List<FavoriteEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
    boolean existsByUserIdAndFlowerId(Long userId, Long flowerId);
    void deleteByUserIdAndFlowerId(Long userId, Long flowerId);
}
