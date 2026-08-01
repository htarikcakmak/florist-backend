package com.cicekci.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Lombok: Bizim için arka planda Getter, Setter metodlarını otomatik yazar.
@Entity // Spring'e "Bu sadece bir kod değil, veritabanında bir tablodur" diyoruz.
@Table(name = "flowers") // PostgreSQL'de tablonun adını 'flowers' olarak belirliyoruz.
public class flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Her çiçeğin eşsiz bir kimlik numarası olacak ve otomatik artacak.

    @Column(nullable = false)
    private String name; // Çiçek adı (boş bırakılamaz)

    @Column(nullable = false)
    private Double price; // Fiyat (boş bırakılamaz)

    private String imagePath; // Görselin konumu

    private String category; // Buket, Saksı vb.

    @Column(columnDefinition = "TEXT") // Uzun açıklamalar sığsın diye TEXT tipini kullanıyoruz
    private String description;

    // ÇOK çiçek BİR mağazaya aittir (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private store store;
}