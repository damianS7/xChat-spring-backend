package com.chat.authentication;

import com.chat.security.SecurityConstant;
import com.chat.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();

        String token = Jwts.builder()
                .setIssuer(SecurityConstant.ISSUER_INFO)
                //.setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuedAt(new Date())
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET_KEY).compact();

        response.addHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY, SecurityConstant.TOKEN_BEARER_PREFIX + " " + token);
        response.setContentType("application/json");
        response.setStatus(200);
        String json = "{ " +
                "\"id\": \"" + user.getId() + "\"," +
                "\"username\": \"" + user.getUsername() + "\"," +
                "\"token\": \"" + token + "\"}";
        response.getWriter().append(json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().append("{ \"error\": \"Bad credentials.\"}");
    }
}
