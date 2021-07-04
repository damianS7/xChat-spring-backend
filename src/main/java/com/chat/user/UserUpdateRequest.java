package com.chat.user;


public class UserUpdateRequest {
    private final String email;
    private final String password;

    public UserUpdateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
