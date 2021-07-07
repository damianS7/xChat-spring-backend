package com.chat.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//@Configuration
public class JwtUtil {

    /**
     * Crea un token unico para el nombre de usuario indicado
     * @param username
     * @return
     */
    public static String generateToken (String username) {
        return Jwts.builder()
                .setIssuer(SecurityConstant.ISSUER_INFO)
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET_KEY).compact();
    }

    /**
     * Se procesa el token y se recupera el usuario.
     * @param token
     * @return El nombre de usuario cifrado en el token
     * @throws ExpiredJwtException
     * @throws MalformedJwtException
     */
    public static String extractUsernameFromToken (String token) throws MalformedJwtException, ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(SecurityConstant.SECRET_KEY)
                .parseClaimsJws(token.replace(SecurityConstant.TOKEN_BEARER_PREFIX, ""))
                .getBody()
                .getSubject();
    }
}
