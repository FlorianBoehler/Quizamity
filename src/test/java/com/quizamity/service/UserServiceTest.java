package com.quizamity.service;

import com.quizamity.dao.UserDao;
import com.quizamity.dto.UserCreateDto;
import com.quizamity.dto.UserResponseDto;
import com.quizamity.dto.UserUpdateDto;
import com.quizamity.mapper.UserMapper;
import com.quizamity.model.Role;
import com.quizamity.model.User;
import com.quizamity.security.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordService passwordService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    private UUID userId;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        role = new Role();
        role.setName("USER");

        user = new User();
        user.setId(userId);
        user.setUsername("john");
        user.setRole(role);
    }

    @Test
    void createUser_shouldCreateUser_whenRoleIsValid() {
        UserCreateDto dto = new UserCreateDto();
        dto.username = "john";
        dto.password = "password123";
        dto.email = "john@example.com";
        dto.roleName = "USER";
        String hashedPassword = "hashedPassword";

        when(roleService.findByName("USER")).thenReturn(Optional.of(role));
        when(passwordService.hash("password123")).thenReturn(hashedPassword);

        userService.createUser(dto);

        verify(userDao).create(any(User.class));
    }

    @Test
    void getUser_shouldReturnDto_whenUserExists() {
        when(userDao.findById(userId)).thenReturn(Optional.of(user));

        Optional<UserResponseDto> result = userService.getUser(userId);

        assertTrue(result.isPresent());
        assertEquals("john", result.get().username);
    }

    @Test
    void getUser_shouldReturnEmpty_whenUserNotFound() {
        when(userDao.findById(userId)).thenReturn(Optional.empty());

        Optional<UserResponseDto> result = userService.getUser(userId);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllUsers_shouldReturnAllMappedUsers() {
        when(userDao.findAll()).thenReturn(List.of(user));

        List<UserResponseDto> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("john", result.get(0).username);
    }

    @Test
    void deleteUser_shouldDeleteUser_whenUserExists() {
        when(userDao.findById(userId)).thenReturn(Optional.of(user));

        boolean result = userService.deleteUser(userId);

        assertTrue(result);
        verify(userDao).delete(user);
    }

    @Test
    void deleteUser_shouldReturnFalse_whenUserNotFound() {
        when(userDao.findById(userId)).thenReturn(Optional.empty());

        boolean result = userService.deleteUser(userId);

        assertFalse(result);
    }

    @Test
    void updateUser_shouldThrowException_whenRoleIsInvalid() {
        UserUpdateDto dto = new UserUpdateDto();
        dto.username = "testuser";
        dto.email = "test@example.com";
        dto.password = "newPassword";
        dto.roleName = "ADMIN";
        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(roleService.findByName("ADMIN")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userId, dto));
    }

    @Test
    void updateUser_shouldReturnFalse_whenUserNotFound() {
        UserUpdateDto dto = new UserUpdateDto();
        dto.username = "testuser";
        dto.email = "test@example.com";
        dto.password = "newPassword";
        dto.roleName = "ADMIN";
        when(userDao.findById(userId)).thenReturn(Optional.empty());

        boolean result = userService.updateUser(userId, dto);

        assertFalse(result);
    }

    @Test
    void findByUsername_shouldReturnUser_whenExists() {
        when(userDao.findByUsername("john")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByUsername("john");

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
    }

    @Test
    void getUserEntity_shouldReturnUser_whenExists() {
        when(userDao.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserEntity(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
    }
}