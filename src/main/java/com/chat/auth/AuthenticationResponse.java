package com.chat.auth;

/**
 * Esta clase es un template que contiene los datos a devolver cuando el usuario se logea
 * con exito en la aplicacion.
 */
public class AuthenticationResponse {
    public Long id;
    public String username;
    public String email;
    public String token;

    public AuthenticationResponse (Long id, String username, String email, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;
    }
}
