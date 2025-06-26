package com.quizamity.service;

import com.quizamity.model.Role;
import com.quizamity.model.User;
import com.quizamity.security.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticateServiceTest {

    @Mock
    private PasswordService passwordService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticateService authenticateService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        // Create a mock user with username, hashed password and role
        Role role = new Role();
        role.setName("USER");

        mockUser = new User();
        mockUser.setUsername("testuser");
        mockUser.setPasswordHash("hashedPassword");
        mockUser.setRole(role);
    }

    @Test
    void authenticate_shouldReturnJwtToken_whenCredentialsAreValid() {
        // Arrange
        when(userService.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
        when(passwordService.verify("plainPassword", "hashedPassword")).thenReturn(true);
        when(passwordService.createJWT("qizamity", "testuser", "USER", 50000)).thenReturn("jwt-token");

        // Act
        String result = authenticateService.authenticate("testuser", "plainPassword");

        // Assert
        assertNotNull(result);
        assertEquals("jwt-token", result);
    }

    @Test
    void authenticate_shouldReturnNull_whenUserNotFound() {
        // Arrange
        when(userService.findByUsername("unknownuser")).thenReturn(Optional.empty());

        // Act
        String result = authenticateService.authenticate("unknownuser", "anyPassword");

        // Assert
        assertNull(result);
        // verify that passwordService.verify() is never called
        verify(passwordService, never()).verify(anyString(), anyString());
    }

    @Test
    void authenticate_shouldReturnNull_whenPasswordIsIncorrect() {
        // Arrange
        when(userService.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
        when(passwordService.verify("wrongPassword", "hashedPassword")).thenReturn(false);

        // Act
        String result = authenticateService.authenticate("testuser", "wrongPassword");

        // Assert
        assertNull(result);
        // verify that createJWT is never called
        verify(passwordService, never()).createJWT(anyString(), anyString(), anyString(), anyLong());
    }
}
