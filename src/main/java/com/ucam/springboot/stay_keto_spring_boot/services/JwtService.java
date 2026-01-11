package com.ucam.springboot.stay_keto_spring_boot.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey; // Importación clave para 2026
import java.util.Date;
import java.util.function.Function;
import java.util.Base64;

@Service
public class JwtService {

    // Secret key Base64 válida (mínimo 256 bits para HS256)
    private static final String SECRET_KEY = Base64.getEncoder()
            .encodeToString("MiClaveMuySeguraDeAlMenos32Caracteres123456".getBytes());

    // Generar token (Sintaxis fluida moderna 0.12.x)
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email) // Antes: setSubject
                .issuedAt(new Date()) // Antes: setIssuedAt
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getSigningKey()) // Ya no requiere SignatureAlgorithm.HS256 explícito
                .compact();
    }

    // Validar token
    public boolean isTokenValid(String token, String email) {
        final String username = extractUsername(token);
        return (username != null && username.equals(email)) && !isTokenExpired(token);
    }

    // Extraer email
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraer cualquier claim (Sintaxis corregida para evitar errores de compilación)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSigningKey()) // Arregla el error de compilación
                .build()
                .parseSignedClaims(token) // Reemplaza a parseClaimsJws
                .getPayload(); // Reemplaza a getBody
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // Método corregido: Retorna SecretKey en lugar de Key
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
