package com.FuriousCustom.security;

import com.FuriousCustom.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {
    private static final String KEY_CARGOS = "CARGOS";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;

    public String getToken(User userEntity) {
        LocalDateTime now = LocalDateTime.now();
        Date nowDT = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expDT = Date.from(now.plusDays(Long.parseLong(expiration)).atZone(ZoneId.systemDefault()).toInstant());


        return Jwts.builder()
                .setIssuer("furious-api")
                .claim(Claims.ID, userEntity.getUserId().toString())
                .setIssuedAt(nowDT)
                .setExpiration(expDT)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if (token != null) {
            token = token.replace("Bearer ", "");

            Claims chaves = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            String userId = chaves.get(Claims.ID, String.class);


            UsernamePasswordAuthenticationToken userDTO = new UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    Collections.emptyList()

            );

            return userDTO;
        }
        return null;
    }
}
