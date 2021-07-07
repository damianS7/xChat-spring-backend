package com.chat.authentication;

import com.chat.security.SecurityConstant;
import com.chat.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Servicio para logearse en la aplicacion
@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse auth (AuthenticationRequest request) throws AuthenticationException {
        String username = request.getUsername();
        String password = request.getPassword();

        Authentication auth;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    username, password, new ArrayList<>())
            );
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Bad credentials"); // 403 Forbidden
            //throw new RuntimeException("Bad credentials XD v2"); // 500 Internal
        }

        String token = Jwts.builder()
                .setIssuer(SecurityConstant.ISSUER_INFO)
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET_KEY).compact();

        User user = (User) auth.getPrincipal();

        return new AuthenticationResponse(
                user.getId(), user.getUsername(), user.getEmail(), token
        );
    }

}
