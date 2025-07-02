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
        Optional<User> userOptional = userService.findByUsername(username);

        // Check if user exists
        if (userOptional.isEmpty()) {
            return null; // User not found
        }

        User user = userOptional.get();

        // Verify password
        if (passwordService.verify(password, user.getPasswordHash())) {
            // Create and return JWT token
            return passwordService.createJWT("quizamity", user.getUsername(), user.getRole().getName(), 50000);
        }

        return null; // Invalid password
    }
}