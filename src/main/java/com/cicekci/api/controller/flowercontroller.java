package com.cicekci.api.controller;

import com.cicekci.api.entity.flower;
import com.cicekci.api.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class flowercontroller {

    @Autowired
    private FlowerService flowerService;

    // Belirli bir dükkanın çiçeklerini getiren kapı
    @GetMapping("/store/{storeId}")
    public List<flower> getFlowersByStore(@PathVariable Long storeId) {
        return flowerService.getFlowersByStore(storeId);
    }

    // Yeni çiçek ekleme kapısı (URL'de hangi dükkana ekleneceğini de belirtiyoruz)
    @PostMapping("/store/{storeId}")
    public flower addFlowerToStore(@PathVariable Long storeId, @RequestBody flower flower) {
        return flowerService.addFlowerToStore(storeId, flower);
    }
}