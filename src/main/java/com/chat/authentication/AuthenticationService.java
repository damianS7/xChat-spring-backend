package com.chat.authentication;

import com.chat.utils.JwtUtil;
import com.chat.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Servicio que permite autenticarse en la aplicacion
 */
@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Metodo que contiene la logica principal del servicio de autenticacion.
     * @param request Peticion que contiene los datos de usuario que intenta autenticarse (usuario y password)
     * @return La respuesta que contiene los datos del usuario autentificado.
     * @throws AuthenticationException Excepcion con el mensaje del fallo arrojado durante la autenticacion
     */
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
        }

        // Creamos el token utilizado para validar al usuario
        String token = JwtUtil.generateToken(username);

        // Usuario autenticado
        User user = (User) auth.getPrincipal();

        // Si necesitasemos mantener la sesion podriamos almacenar los datos
        // Pero esto no tiene sentido en si usamos sesiones sin estado basado tokens
        // SecurityContextHolder.getContext().setAuthentication(auth);

        // Enviamos al usuario de vuelta los datos necesarios para el cliente
        return new AuthenticationResponse(
                user.getId(), user.getUsername(), user.getEmail(), token
        );
    }

}
