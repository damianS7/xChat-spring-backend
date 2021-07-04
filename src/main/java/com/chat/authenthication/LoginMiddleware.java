package com.chat.authenthication;

public class LoginMiddleware {

    private boolean auth = false;

    public boolean isLogged() {
        return this.auth;
    }
}
