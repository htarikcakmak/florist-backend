package com.cicekci.api.repository;

import com.cicekci.api.entity.store;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository sayesinde kaydet, sil, bul gibi tüm metodlar hazır gelir!
public interface storerepository extends JpaRepository<store, Long> {
}