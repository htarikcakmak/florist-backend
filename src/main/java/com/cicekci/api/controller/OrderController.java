package com.cicekci.api.controller;

import com.cicekci.api.entity.OrderEntity;
import com.cicekci.api.entity.OrderItemEntity;
import com.cicekci.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
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
    public ResponseEntity<List<OrderEntity>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<OrderEntity>> getStoreOrders(@PathVariable Long storeId) {
        return ResponseEntity.ok(orderRepository.findByStoreIdOrderByCreatedAtDesc(storeId));
    }
}
