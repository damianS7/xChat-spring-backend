package com.chat.authentication;

public class LoginMiddleware {

    private boolean auth = false;

    public boolean isLogged() {
        return this.auth;
    }
}
