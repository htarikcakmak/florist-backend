package com.cicekci.api.controller;

import com.cicekci.api.entity.store;
import com.cicekci.api.repository.storerepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@Tag(name = "Stores", description = "Mağaza listeleme ve oluşturma")
public class storecontroller {

    @Autowired
    private storerepository storeRepository; // Köprümüzü çağırıyoruz

    // Dükkanları listeleme kapısı (GET)
    @GetMapping
    public List<store> getAllStores() {
        return storeRepository.findAll();
    }

    // Yeni dükkan ekleme kapısı (POST)
    @PostMapping
    public store createStore(@RequestBody store store) {
        return storeRepository.save(store);
    }
}