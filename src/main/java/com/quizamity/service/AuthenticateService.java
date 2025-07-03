package com.quizamity.service;

import com.quizamity.model.User;
import com.quizamity.security.PasswordService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Optional;

@Stateless
public class AuthenticateService {

    private static final Logger LOGGER = Logger.getLogger(AuthenticateService.class.getName());

    @Inject
    private PasswordService passwordService;

    @Inject
    private UserService userService;

    public String authenticate(String username, String password) {
        LOGGER.info("=== Starting authentication for user: " + username + " ===");

        try {
            // Step 1: Check if services are injected
            if (userService == null) {
                LOGGER.severe("ERROR: UserService is null!");
                return null;
            }
            if (passwordService == null) {
                LOGGER.severe("ERROR: PasswordService is null!");
                return null;
            }
            LOGGER.info("✓ All services injected successfully");

            // Step 2: Find user
            LOGGER.info("Looking up user: " + username);
            Optional<User> userOptional = userService.findByUsername(username);

            if (userOptional == null) {
                LOGGER.severe("ERROR: findByUsername returned null instead of Optional!");
                return null;
            }

            // Step 3: Check if user exists
            if (userOptional.isEmpty()) {
                LOGGER.warning("User not found: " + username);
                return null;
            }

            User user = userOptional.get();
            if (user == null) {
                LOGGER.severe("ERROR: User object is null even though Optional is not empty!");
                return null;
            }

            LOGGER.info("✓ User found: " + user.getUsername());

            // Step 4: Check password hash
            String passwordHash = user.getPasswordHash();
            if (passwordHash == null) {
                LOGGER.severe("ERROR: User password hash is null!");
                return null;
            }
            LOGGER.info("✓ Password hash retrieved (length: " + passwordHash.length() + ")");

            // Step 5: Check role
            if (user.getRole() == null) {
                LOGGER.severe("ERROR: User role is null!");
                return null;
            }
            String roleName = user.getRole().getName();
            if (roleName == null) {
                LOGGER.severe("ERROR: Role name is null!");
                return null;
            }
            LOGGER.info("✓ User role: " + roleName);

            // Step 6: Verify password
            LOGGER.info("Verifying password...");
            boolean passwordValid = passwordService.verify(password, passwordHash);
            LOGGER.info("Password verification result: " + passwordValid);

            if (!passwordValid) {
                LOGGER.warning("Password verification failed for user: " + username);
                return null;
            }

            // Step 7: Create JWT token
            LOGGER.info("Creating JWT token...");
            String token = passwordService.createJWT("quizamity", user.getUsername(), roleName, 50000);

            if (token == null) {
                LOGGER.severe("ERROR: JWT token creation returned null!");
                return null;
            }

            LOGGER.info("✓ JWT token created successfully (length: " + token.length() + ")");
            LOGGER.info("=== Authentication successful for user: " + username + " ===");

            return token;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ERROR during authentication for user: " + username, e);
            return null;
        }
    }
}