package com.cicekci.api.controller;

import com.cicekci.api.entity.FavoriteEntity;
import com.cicekci.api.repository.FavoriteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "Favorites", description = "Favori sistemi")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @PostMapping
    @Operation(summary = "Favorilere ekle", description = "Bir çiçeği kullanıcının favorilerine ekler")
    public ResponseEntity<FavoriteEntity> addFavorite(@RequestBody FavoriteEntity favorite) {
        FavoriteEntity savedFavorite = favoriteRepository.save(favorite);
        return ResponseEntity.ok(savedFavorite);
    }

    @DeleteMapping("/{userId}/{flowerId}")
    @Transactional
    @Operation(summary = "Favorilerden kaldır", description = "Bir çiçeği kullanıcının favorilerinden kaldırır")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long userId, @PathVariable Long flowerId) {
        favoriteRepository.deleteByUserIdAndFlowerId(userId, flowerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Kullanıcının favorilerini getir", description = "Belirtilen kullanıcının tüm favori çiçeklerini getirir")
    public ResponseEntity<List<FavoriteEntity>> getUserFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @GetMapping("/check/{userId}/{flowerId}")
    @Operation(summary = "Favori kontrolü", description = "Bir çiçeğin kullanıcının favorilerinde olup olmadığını kontrol eder")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Long userId, @PathVariable Long flowerId) {
        return ResponseEntity.ok(favoriteRepository.existsByUserIdAndFlowerId(userId, flowerId));
    }
}
