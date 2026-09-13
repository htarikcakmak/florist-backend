package com.cicekci.api.security;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    @DisplayName("Token üretilip doğrulanabilmeli")
    void generateAndValidateToken() {
        String token = jwtUtil.generateToken(1L, "test@example.com", "CUSTOMER");

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    @DisplayName("Token'dan email okunabilmeli")
    void getEmailFromToken() {
        String token = jwtUtil.generateToken(42L, "ali@cicek.com", "SELLER");

        String email = jwtUtil.getEmailFromToken(token);
        assertEquals("ali@cicek.com", email);
    }

    @Test
    @DisplayName("Token'dan claims okunabilmeli")
    void parseTokenClaims() {
        String token = jwtUtil.generateToken(7L, "ayse@test.com", "CUSTOMER");

        Claims claims = jwtUtil.parseToken(token);
        assertEquals("ayse@test.com", claims.getSubject());
        assertEquals(7, claims.get("userId", Integer.class));
        assertEquals("CUSTOMER", claims.get("role", String.class));
    }

    @Test
    @DisplayName("Geçersiz token false döndürmeli")
    void invalidToken_returnsFalse() {
        assertFalse(jwtUtil.validateToken("tamamen.gecersiz.token"));
        assertFalse(jwtUtil.validateToken(""));
        assertFalse(jwtUtil.validateToken(null));
    }
}
