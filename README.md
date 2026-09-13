# 🌿 Flowerist API — Spring Boot Backend

**Flowerist API**, çiçek e-ticaret uygulamasının arka uç servisidir. Spring Boot ile geliştirilmiş olup Neon (PostgreSQL) bulut veritabanıyla çalışır.

## 🔌 API Endpoints

### Auth (Kimlik Doğrulama)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| `POST` | `/api/auth/register` | Yeni kullanıcı kaydı |
| `POST` | `/api/auth/login` | Giriş (email + şifre) |

### Flowers (Çiçekler)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| `GET` | `/api/flowers/store/{storeId}` | Bir mağazanın tüm çiçeklerini getir |
| `POST` | `/api/flowers/store/{storeId}` | Mağazaya yeni çiçek ekle |

### Stores (Mağazalar)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| `GET` | `/api/stores` | Tüm mağazaları listele |
| `POST` | `/api/stores` | Yeni mağaza oluştur |

### Orders (Siparişler)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| `POST` | `/api/orders` | Yeni sipariş oluştur |
| `GET` | `/api/orders/user/{userId}` | Müşterinin geçmiş siparişleri |
| `GET` | `/api/orders/store/{storeId}` | Mağazaya gelen siparişler |

## 🛠 Teknoloji

- **Java 21** + **Spring Boot 4.1**
- **Spring Data JPA** — ORM (Hibernate)
- **PostgreSQL** — Neon Cloud Database
- **Lombok** — Boilerplate azaltıcı
- **Maven** — Build sistemi

## 🚀 Kurulum

### 1. Veritabanı Şifresini Ayarla

```bash
# Windows PowerShell
$env:DB_PASSWORD="neon_sifrenizi_buraya_yazin"

# Linux / macOS
export DB_PASSWORD=neon_sifrenizi_buraya_yazin
```

### 2. Projeyi Derle & Çalıştır

```bash
# Derleme
.\mvnw clean package -DskipTests

# Çalıştır
java -jar target/api-0.0.1-SNAPSHOT.jar
```

Sunucu `http://localhost:8080` üzerinde ayağa kalkar.

## 📁 Proje Yapısı

```
src/main/java/com/cicekci/api/
├── ApiApplication.java              # Spring Boot giriş noktası
├── controller/
│   ├── AuthController.java          # Giriş / Kayıt endpoint'leri
│   ├── flowercontroller.java        # Çiçek CRUD
│   ├── storecontroller.java         # Mağaza CRUD
│   └── OrderController.java         # Sipariş yönetimi
├── entity/
│   ├── User.java                    # Kullanıcı tablosu
│   ├── flower.java                  # Çiçek tablosu
│   ├── store.java                   # Mağaza tablosu
│   ├── OrderEntity.java             # Sipariş tablosu
│   └── OrderItemEntity.java         # Sipariş kalemleri
├── repository/
│   ├── UserRepository.java
│   ├── flowerrepository.java
│   ├── storerepository.java
│   └── OrderRepository.java
└── service/
    └── FlowerService.java           # Çiçek iş mantığı
```

## 🔒 Güvenlik Notları

> ✅ **Güvenlik altyapısı entegre edilmiştir.**

- ✅ Şifreler **BCrypt** ile hash'lenerek veritabanına kaydedilir.
- ✅ Oturum yönetimi **JWT (JSON Web Token)** ile stateless olarak yapılır.
- ✅ **CORS** yapılandırması `SecurityConfig` içinde aktiftir.
- Veritabanı şifresi `${DB_PASSWORD}` ortam değişkeni olarak tutulur.
- `.gitignore` dosyasına hassas konfigürasyon dosyaları eklenmiştir.

## ☁️ Cloud Deploy

Proje **Railway**, **Render** veya **Docker** ile deploy edilebilir:

```bash
# Railway
railway up

# Render — render.yaml otomatik algılanır
# Dashboard'dan "New Web Service" > GitHub repo bağla

# Docker
docker-compose up -d
```

Ortam değişkenlerini platform dashboard'unda ayarla:
- `DB_PASSWORD` — PostgreSQL şifresi
- `SPRING_DATASOURCE_URL` — JDBC bağlantı URL'i
- `SPRING_PROFILES_ACTIVE=prod` — Production profili

## 📄 Lisans

MIT
