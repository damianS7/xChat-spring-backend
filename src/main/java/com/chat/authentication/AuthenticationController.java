package com.chat.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    //@CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/users/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) throws AuthenticationException {
        return authenticationService.auth(request);
    }
}
