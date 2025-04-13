package com.quizamity.service;

import com.quizamity.dto.UserCreateDTO;
import com.quizamity.dto.UserResponseDTO;
import com.quizamity.model.User;
import com.quizamity.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Transactional
    public UserResponseDTO createUser(UserCreateDTO userDTO) throws Exception {
        // Check if the username already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }

        // Check if the email already exists
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        // Hash the password
        String passwordHash = hashPassword(userDTO.getPassword());

        // Create a new user entity
        User user = new User(
                userDTO.getUsername(),
                hashPassword(userDTO.getPassword()),
                userDTO.getEmail(),
                userDTO.getRole() != null ? userDTO.getRole() : "STUDENT" // Default role if not provided
        );

        // Save the user to the database
        user = userRepository.save(user);

        // Create a response DTO
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    // Password hashing function
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}
