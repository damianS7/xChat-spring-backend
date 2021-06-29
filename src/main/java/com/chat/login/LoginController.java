package com.chat.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class LoginController {

    @PostMapping("login")
    public boolean login(){
        return true;
    }

    @PostMapping("logout")
    public boolean logout(){
        return true;
    }
}
