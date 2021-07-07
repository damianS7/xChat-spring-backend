package com.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class ChatApplication /*implements ErrorController*/ {

    // No mostrar nada en errores 404
    /*@RequestMapping("/error")
    public String error () {
        return "";
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

}
