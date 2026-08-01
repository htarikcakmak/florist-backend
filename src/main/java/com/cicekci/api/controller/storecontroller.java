package com.cicekci.api.controller;

import com.cicekci.api.entity.store;
import com.cicekci.api.repository.storerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Buranın dış dünyaya açık bir API kapısı olduğunu belirtir
@RequestMapping("/api/stores") // Bu kapının adresi
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