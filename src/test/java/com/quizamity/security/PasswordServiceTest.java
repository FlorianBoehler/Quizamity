package com.quizamity.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import io.jsonwebtoken.*;

import java.util.UUID;

public class PasswordServiceTest {

    private PasswordService passwordService;

    @BeforeEach
    public void setUp() {
        passwordService = new PasswordService();
    }

    @Test
    public void hash_shouldReturnHashedPassword_whenPlainPasswordIsProvided() {
        // Given
        String plainPassword = "mySecretPassword";

        // When
        String hashedPassword = passwordService.hash(plainPassword);

        // Then
        assertNotNull(hashedPassword, "Hashed password should not be null");
        assertNotEquals(plainPassword, hashedPassword, "Hashed password should differ from plain password");
        assertTrue(hashedPassword.startsWith("$2a$"), "Hashed password should use bcrypt format");
    }

    @Test
    public void verify_shouldReturnTrue_whenCorrectPasswordIsProvided() {
        // Given
        String plainPassword = "securePassword123";
        String hashedPassword = passwordService.hash(plainPassword);

        // When
        boolean result = passwordService.verify(plainPassword, hashedPassword);

        // Then
        assertTrue(result, "Password verification should succeed with correct password");
    }

    @Test
    public void verify_shouldReturnFalse_whenIncorrectPasswordIsProvided() {
        // Given
        String plainPassword = "correctPassword";
        String hashedPassword = passwordService.hash(plainPassword);
        String wrongPassword = "wrongPassword";

        // When
        boolean result = passwordService.verify(wrongPassword, hashedPassword);

        // Then
        assertFalse(result, "Password verification should fail with incorrect password");
    }


    // Helper method to access private static secret key
    private String getSecretKey() {
        // This must match the static value in PasswordService
        return "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    }
}
