package com.chat.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//@Configuration
public class JwtUtil {

    /**
     * Crea un token unico para el nombre de usuario indicado
     * @param username Nombre del usuario que va a ser cifrado en el token
     * @return El token generado con los datos de usuario cifrados en el token
     */
    public static String generateToken (String username) {
        return Jwts.builder()
                .setIssuer(SecurityConstant.ISSUER_INFO)
                .setIssuedAt(new Date())
                .setSubject(username)
                // Puede ser de utilidad mas adelante
                // .setId(userId)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET_KEY).compact();
    }

    /**
     * Se procesa el token y se recupera el usuario.
     * @param token Contiene el token de seguridad que se envia con la peticion
     * @return El nombre de usuario cifrado en el token
     * @throws ExpiredJwtException El token de seguridad ha expirado
     * @throws MalformedJwtException El formato del token no es correcto
     */
    public static String extractUsernameFromToken (String token) throws MalformedJwtException, ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(SecurityConstant.SECRET_KEY)
                .parseClaimsJws(token.replace(SecurityConstant.TOKEN_BEARER_PREFIX, ""))
                .getBody()
                .getSubject();
    }
}
