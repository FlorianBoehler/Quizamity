package com.quizamity.mapper;

import com.quizamity.dto.UserCreateDto;
import com.quizamity.dto.UserResponseDto;
import com.quizamity.dto.UserUpdateDto;
import com.quizamity.model.Role;
import com.quizamity.model.User;
import com.quizamity.security.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserMapperTest {

    @Mock
    private PasswordService passwordService;

    private final UUID userId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toEntity_shouldMapCorrectly_whenValidInputProvided() {
        // Given
        UserCreateDto dto = new UserCreateDto();
        dto.username = "john_doe";
        dto.email = "john@example.com";

        Role role = new Role();
        role.setName("USER");

        String hashedPassword = "hashed_password";

        // When
        User user = UserMapper.toEntity(dto, role, hashedPassword);

        // Then
        assertEquals("john_doe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
        assertEquals(role, user.getRole());
        assertEquals("hashed_password", user.getPasswordHash());
    }

    @Test
    void toDto_shouldMapCorrectly_whenValidUserProvided() {
        // Given
        Role role = new Role();
        role.setName("ADMIN");

        User user = new User("jane_doe", "hashed123", "jane@example.com", role);
        user.setId(userId);

        // When
        UserResponseDto dto = UserMapper.toDto(user);

        // Then
        assertEquals(userId, dto.id);
        assertEquals("jane_doe", dto.username);
        assertEquals("jane@example.com", dto.email);
        assertEquals("ADMIN", dto.roleName);
    }

    @Test
    void updateEntity_shouldUpdateFieldsWithoutPassword_whenPasswordIsNull() {
        // Given
        Role originalRole = new Role();
        originalRole.setName("USER");

        Role newRole = new Role();
        newRole.setName("MODERATOR");

        User user = new User("old_name", "old_hash", "old@example.com", originalRole);
        user.setId(userId);

        UserUpdateDto dto = new UserUpdateDto();
        dto.username = "new_name";
        dto.email = "new@example.com";
        dto.password = null;

        // When
        UserMapper.updateEntity(user, dto, newRole, passwordService);

        // Then
        assertEquals("new_name", user.getUsername());
        assertEquals("new@example.com", user.getEmail());
        assertEquals(newRole, user.getRole());
        assertEquals("old_hash", user.getPasswordHash());
        verify(passwordService, never()).hash(any());
    }

    @Test
    void updateEntity_shouldHashAndSetPassword_whenPasswordIsProvided() {
        // Given
        Role role = new Role();
        role.setName("USER");

        User user = new User("user", "old_hash", "user@example.com", role);
        user.setId(userId);

        UserUpdateDto dto = new UserUpdateDto();
        dto.username = "updated_user";
        dto.email = "updated@example.com";
        dto.password = "new_password";

        when(passwordService.hash("new_password")).thenReturn("new_hashed_password");

        // When
        UserMapper.updateEntity(user, dto, role, passwordService);

        // Then
        assertEquals("updated_user", user.getUsername());
        assertEquals("updated@example.com", user.getEmail());
        assertEquals(role, user.getRole());
        assertEquals("new_hashed_password", user.getPasswordHash());
        verify(passwordService).hash("new_password");
    }

    @Test
    void updateEntity_shouldNotHashPassword_whenPasswordIsBlank() {
        // Given
        Role role = new Role();
        User user = new User("user", "old_hash", "user@example.com", role);
        user.setId(userId);

        UserUpdateDto dto = new UserUpdateDto();
        dto.username = "newuser";
        dto.email = "new@example.com";
        dto.password = "   "; // blank password

        // When
        UserMapper.updateEntity(user, dto, role, passwordService);

        // Then
        assertEquals("newuser", user.getUsername());
        assertEquals("new@example.com", user.getEmail());
        assertEquals("old_hash", user.getPasswordHash());
        verify(passwordService, never()).hash(any());
    }
}
