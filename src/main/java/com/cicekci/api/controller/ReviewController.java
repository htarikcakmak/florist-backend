package com.cicekci.api.controller;

import com.cicekci.api.entity.ReviewEntity;
import com.cicekci.api.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Reviews", description = "Yorum sistemi")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping
    @Operation(summary = "Yeni yorum oluştur", description = "Bir çiçek/mağaza için yorum ve puan ekler (auth gerektirir)")
    public ResponseEntity<ReviewEntity> createReview(@RequestBody ReviewEntity review) {
        ReviewEntity savedReview = reviewRepository.save(review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/flower/{flowerId}")
    @Operation(summary = "Çiçeğe ait yorumları getir", description = "Belirtilen çiçeğe yapılmış tüm yorumları getirir")
    public ResponseEntity<List<ReviewEntity>> getFlowerReviews(@PathVariable Long flowerId) {
        return ResponseEntity.ok(reviewRepository.findByFlowerIdOrderByCreatedAtDesc(flowerId));
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "Mağazaya ait yorumları getir", description = "Belirtilen mağazaya yapılmış tüm yorumları getirir (satıcı bu yorumları görür)")
    public ResponseEntity<List<ReviewEntity>> getStoreReviews(@PathVariable Long storeId) {
        return ResponseEntity.ok(reviewRepository.findByStoreIdOrderByCreatedAtDesc(storeId));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Kullanıcının yorumlarını getir", description = "Belirtilen kullanıcının yaptığı tüm yorumları getirir")
    public ResponseEntity<List<ReviewEntity>> getUserReviews(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }
}
