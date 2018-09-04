package com.zsc.mall1.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtUtil {

    private static final long TIME_OUT = 1800 * 1000L;
    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512

    public static String CreatJWT(String subject){

        Date now = new Date();
        Date thirtyMinutes = new Date(System.currentTimeMillis() + TIME_OUT);

        return  Jwts.builder()
                .setSubject(subject) // 主题
                .setIssuedAt(now) // 签发时间
                .setExpiration(thirtyMinutes) // 过期时间
                .signWith(key)
                .compact();

    }
    public static String parseJWT(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

        }catch (JwtException ex){
            System.out.println("签证失效");
            return null;
        }
    }
}
