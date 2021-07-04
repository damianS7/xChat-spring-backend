package com.chat.authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {

    @GetMapping("/login")
    public String index () {
        return "Envia tus credenciales via post a /login";
    }

    @PostMapping("/login")
    public String login(){
        return "Logeado con exito!";
    }

    @PostMapping("/logout")
    public boolean logout(){
        return true;
    }
}
