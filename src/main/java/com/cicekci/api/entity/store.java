package com.cicekci.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "stores")
public class store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mağazanın eşsiz ID'si

    @Column(nullable = false)
    private String name; // Mağaza adı (Örn: Çiçek Sepetim)

    private String vendorName; // Satıcının adı

    // İŞTE MÜHENDİSLİK KISMI: BİR mağazanın ÇOK çiçeği olur (One-to-Many)
    @JsonIgnore // (İleride veriler telefona giderken sonsuz döngüye girmesin diye kilitliyoruz)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<flower> flowers;
}