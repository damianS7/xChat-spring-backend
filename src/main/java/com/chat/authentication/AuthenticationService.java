package com.chat.authentication;

import com.chat.security.JwtUtil;
import com.chat.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

        // Creamos el token utilizado para validar al usuario
        String token = JwtUtil.generateToken(username);

        User user = (User) auth.getPrincipal();

        // Esto no tiene sentido en si usamos sesiones sin estado y tokens??
        // SecurityContextHolder.getContext().setAuthentication(auth);

        // Enviamos al usuario de vuelta los datos necesarios para el cliente
        return new AuthenticationResponse(
                user.getId(), user.getUsername(), user.getEmail(), token
        );
    }

}
