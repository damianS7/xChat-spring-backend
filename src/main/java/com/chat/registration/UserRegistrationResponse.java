package com.chat.registration;

public class UserRegistrationResponse {
    public Long id;
    public String username;
    public String email;

    public UserRegistrationResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
