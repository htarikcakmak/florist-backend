package com.cicekci.api.controller;

import com.cicekci.api.entity.flower;
import com.cicekci.api.entity.store;
import com.cicekci.api.repository.flowerrepository;
import com.cicekci.api.repository.storerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class flowercontroller {

    @Autowired
    private flowerrepository flowerRepository;
    
    @Autowired
    private storerepository storerepository;

    // Belirli bir dükkanın çiçeklerini getiren kapı
    @GetMapping("/store/{storeId}")
    public List<flower> getFlowersByStore(@PathVariable Long storeId) {
        return flowerRepository.findByStoreId(storeId);
    }

    // Yeni çiçek ekleme kapısı (URL'de hangi dükkana ekleneceğini de belirtiyoruz)
    @PostMapping("/store/{storeId}")
    public flower addFlowerToStore(@PathVariable Long storeId, @RequestBody flower flower) {
        // Önce dükkanı buluyoruz, sonra çiçeği o dükkana bağlayıp kaydediyoruz
        store store = storerepository.findById(storeId).orElseThrow();
        flower.setStore(store);
        return flowerRepository.save(flower);
    }
}