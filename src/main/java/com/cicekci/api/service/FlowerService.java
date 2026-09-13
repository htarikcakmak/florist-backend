package com.cicekci.api.service;

import com.cicekci.api.entity.flower;
import com.cicekci.api.entity.store;
import com.cicekci.api.repository.flowerrepository;
import com.cicekci.api.repository.storerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerService {

    @Autowired
    private flowerrepository flowerRepository;
    
    @Autowired
    private storerepository storeRepository;

    /**
     * Belirli bir dükkana ait tüm çiçekleri getirir.
     */
    public List<flower> getFlowersByStore(Long storeId) {
        return flowerRepository.findByStoreId(storeId);
    }

    /**
     * Yeni bir çiçeği dükkana bağlayarak veritabanına kaydeder.
     * Gelecekte eklenecek iş mantıkları (validasyonlar vb.) burada yer alacaktır.
     */
    public flower addFlowerToStore(Long storeId, flower newFlower) {
        store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new RuntimeException("Store not found with id: " + storeId));
        
        newFlower.setStore(store);
        
        // Çevirilerin (nameEn, nameEs vb.) veya diğer bilgilerin 
        // işlenmesi (örneğin frontend'den gelmediyse null kalması) standart bir davranıştır.
        // İhtiyaç halinde validasyon kuralları buraya eklenebilir.
        
        return flowerRepository.save(newFlower);
    }
}
