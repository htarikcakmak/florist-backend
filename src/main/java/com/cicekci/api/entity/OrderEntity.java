package com.cicekci.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // Siparişi veren müşteri ID'si

    @Column(nullable = false)
    private Long storeId; // Siparişin verildiği mağaza ID'si

    @Column(nullable = false)
    private Double totalPrice;

    private String status; // "PENDING", "COMPLETED", "CANCELLED"

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItemEntity> items;
}
