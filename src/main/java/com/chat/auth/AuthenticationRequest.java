package com.chat.auth;

/**
 * Esta clase se utiliza como plantilla para los datos que debe enviar el usuario en su peticion.
 */
public class AuthenticationRequest {
    // Estos metodos tambien podrian ser publicos y eliminar los metodos ...
    private final String username;
    private final String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
