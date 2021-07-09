package com.chat.authentication;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepcion lanzada cuando ocurre un error durante la autenticacion
 */
public class AuthenticationFailedException extends AuthenticationException {
    public AuthenticationFailedException(String msg) {
        super(msg);
    }
}
