package com.quizamity.service;

import com.quizamity.model.User;
import com.quizamity.security.PasswordService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Stateless
public class AuthenticateService {

    @Inject
    private PasswordService passwordService;

    @Inject
    private UserService userService;

    public String authenticate(String username, String password) {
        Optional<User> user = userService.findByUsername(username);
        String jwtToken = null;
        if (passwordService.verify(password, userService.findByUsername(username).get().getPasswordHash())) {

            jwtToken = passwordService.createJWT("qizamity", user.get().getUsername(), user.get().getRole().getName(), 50000);
        }
        return jwtToken;
    }

}
