package com.cicekci.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // Yorumu yapan kullanıcı ID'si

    @Column(nullable = false)
    private String userName; // Yorumu yapan kullanıcı adı

    @Column(nullable = false)
    private Long flowerId; // Yorum yapılan çiçek ID'si

    @Column(nullable = false)
    private Long storeId; // Yorum yapılan mağaza ID'si

    @Column(nullable = false)
    private int rating; // 1-5 arası puan

    @Column(length = 1000)
    private String comment; // Yorum metni

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
