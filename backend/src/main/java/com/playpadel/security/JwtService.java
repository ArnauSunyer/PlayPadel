package com.playpadel.security;

import com.playpadel.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final SecretKey clau;
    private final long expiracioMinuts;

    public JwtService(@Value("${playpadel.jwt.secret}") String secret,
                      @Value("${playpadel.jwt.expiracio-minuts}") long expiracioMinuts) {
        this.clau = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expiracioMinuts = expiracioMinuts;
    }

    // Crea un token signat amb les dades de l'usuari
    public String generarToken(User user) {
        Instant ara = Instant.now();
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .claim("rol", user.getRol().name())
                .issuedAt(Date.from(ara))
                .expiration(Date.from(ara.plus(expiracioMinuts, ChronoUnit.MINUTES)))
                .signWith(clau)
                .compact();
    }

    // Comprova la signatura i la caducitat, i retorna el contingut del token
    public Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(clau)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public long getExpiracioSegons() {
        return expiracioMinuts * 60;
    }
}