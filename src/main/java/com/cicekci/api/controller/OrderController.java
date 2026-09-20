package com.cicekci.api.controller;

import com.cicekci.api.entity.OrderEntity;
import com.cicekci.api.entity.OrderItemEntity;
import com.cicekci.api.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Sipariş yönetimi")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    @Operation(summary = "Yeni sipariş oluştur")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");
        
        if (order.getItems() != null) {
            for (OrderItemEntity item : order.getItems()) {
                item.setOrder(order);
            }
        }
        
        OrderEntity savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Kullanıcının siparişlerini getir")
    public ResponseEntity<List<OrderEntity>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "Mağazanın siparişlerini getir")
    public ResponseEntity<List<OrderEntity>> getStoreOrders(@PathVariable Long storeId) {
        return ResponseEntity.ok(orderRepository.findByStoreIdOrderByCreatedAtDesc(storeId));
    }

    @PutMapping("/{orderId}/status")
    @Operation(summary = "Sipariş durumunu güncelle (PENDING → PREPARING → SHIPPED → DELIVERED)")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        if (newStatus == null || newStatus.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Status alanı gerekli"));
        }

        return orderRepository.findById(orderId)
            .map(order -> {
                order.setStatus(newStatus.toUpperCase());
                orderRepository.save(order);
                return ResponseEntity.ok(order);
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
