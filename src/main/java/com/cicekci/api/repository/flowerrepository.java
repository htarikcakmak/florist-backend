package com.cicekci.api.repository;

import com.cicekci.api.entity.flower;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface flowerrepository extends JpaRepository<flower, Long> {
    // Özel bir komut: Mağazanın ID'sine göre o dükkanın çiçeklerini getir!
    List<flower> findByStoreId(Long storeId);
}