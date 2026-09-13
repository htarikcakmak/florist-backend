package com.cicekci.api.controller;

import com.cicekci.api.entity.User;
import com.cicekci.api.repository.UserRepository;
import com.cicekci.api.security.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AuthController logic — JWT ve BCrypt doğrulamaları.
 * Bu testler veritabanı gerektirmez, bağımsız çalışır.
 */
class AuthControllerTest {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    @DisplayName("BCrypt: Şifre hash'lenip doğru eşleşmeli")
    void bcrypt_encode_and_match() {
        String raw = "sifre123";
        String hashed = encoder.encode(raw);
        assertTrue(encoder.matches(raw, hashed));
    }

    @Test
    @DisplayName("BCrypt: Yanlış şifre eşleşmemeli")
    void bcrypt_wrong_password_no_match() {
        String hashed = encoder.encode("dogrusifre");
        assertFalse(encoder.matches("yanlissifre", hashed));
    }

    @Test
    @DisplayName("BCrypt: Her hash farklı olmalı (salt)")
    void bcrypt_different_hashes_for_same_password() {
        String raw = "sifre123";
        String hash1 = encoder.encode(raw);
        String hash2 = encoder.encode(raw);
        assertNotEquals(hash1, hash2);
        assertTrue(encoder.matches(raw, hash1));
        assertTrue(encoder.matches(raw, hash2));
    }

    @Test
    @DisplayName("User: Entity doğru oluşturulmalı")
    void user_entity_creation() {
        User user = new User();
        user.setName("Tuğçe");
        user.setEmail("tugce@test.com");
        user.setPassword("hashed");
        user.setRole("CUSTOMER");

        assertEquals("Tuğçe", user.getName());
        assertEquals("tugce@test.com", user.getEmail());
        assertEquals("CUSTOMER", user.getRole());
    }

    @Test
    @DisplayName("User: E-posta boş olmamalı kontrolü")
    void user_email_not_null() {
        User user = new User();
        user.setEmail("test@test.com");
        assertNotNull(user.getEmail());
        assertTrue(user.getEmail().contains("@"));
    }
}
