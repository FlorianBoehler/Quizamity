package com.quizamity.service;

import com.quizamity.dao.RoleDao;
import com.quizamity.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleService roleService;

    private Role sampleRole;
    private UUID roleId;

    @BeforeEach
    void setUp() {
        roleId = UUID.randomUUID();
        sampleRole = new Role();
        sampleRole.setId(roleId);
        sampleRole.setName("Admin");
    }

    @Test
    void createRole_shouldCallDaoCreate_withGivenRole() {
        // Act
        roleService.createRole(sampleRole);

        // Assert
        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleDao, times(1)).create(roleCaptor.capture());
        assertEquals(sampleRole, roleCaptor.getValue(), "The role passed to the DAO should match the one provided.");
    }

    @Test
    void getRole_shouldReturnRole_whenRoleExists() {
        // Arrange
        when(roleDao.findById(roleId)).thenReturn(Optional.of(sampleRole));

        // Act
        Optional<Role> result = roleService.getRole(roleId);

        // Assert
        assertTrue(result.isPresent(), "Expected role to be found.");
        assertEquals(sampleRole, result.get(), "Returned role should match the mock.");
    }

    @Test
    void getRole_shouldReturnEmpty_whenRoleDoesNotExist() {
        // Arrange
        when(roleDao.findById(roleId)).thenReturn(Optional.empty());

        // Act
        Optional<Role> result = roleService.getRole(roleId);

        // Assert
        assertFalse(result.isPresent(), "Expected no role to be found.");
    }

    @Test
    void findByName_shouldReturnRole_whenNameExists() {
        // Arrange
        when(roleDao.findByName("Admin")).thenReturn(Optional.of(sampleRole));

        // Act
        Optional<Role> result = roleService.findByName("Admin");

        // Assert
        assertTrue(result.isPresent(), "Expected role with name 'Admin' to be found.");
        assertEquals(sampleRole, result.get(), "Returned role should match the mock.");
    }

    @Test
    void findByName_shouldReturnEmpty_whenNameDoesNotExist() {
        // Arrange
        when(roleDao.findByName("NonExistent")).thenReturn(Optional.empty());

        // Act
        Optional<Role> result = roleService.findByName("NonExistent");

        // Assert
        assertFalse(result.isPresent(), "Expected no role to be found for nonexistent name.");
    }

    @Test
    void getAllRoles_shouldReturnAllRolesFromDao() {
        // Arrange
        List<Role> roles = Arrays.asList(sampleRole, new Role());
        when(roleDao.findAll()).thenReturn(roles);

        // Act
        List<Role> result = roleService.getAllRoles();

        // Assert
        assertEquals(2, result.size(), "Should return all roles from DAO.");
        assertEquals(roles, result, "Returned list should match the DAO result.");
    }
}
