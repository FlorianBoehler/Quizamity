package com.quizamity.service;

import com.quizamity.dao.UserDao;
import com.quizamity.dto.UserCreateDto;
import com.quizamity.dto.UserResponseDto;
import com.quizamity.dto.UserUpdateDto;
import com.quizamity.mapper.UserMapper;
import com.quizamity.model.Role;
import com.quizamity.model.User;
import com.quizamity.security.PasswordService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private PasswordService passwordService;

    @Inject
    private RoleService roleService;

    public void createUser(UserCreateDto dto) {
        Role role = roleService.findByName(dto.roleName)
                .orElseThrow(() -> new IllegalArgumentException("Ungültige Rolle: " + dto.roleName));
        String hashed = passwordService.hash(dto.password);
        User user = UserMapper.toEntity(dto, role, hashed);
        userDao.create(user);
    }

    public Optional<UserResponseDto> getUser(UUID id) {
        return userDao.findById(id)
                .map(UserMapper::toDto);
    }

    public List<UserResponseDto> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean deleteUser(UUID id) {
        return userDao.findById(id).map(user -> {
            userDao.delete(user);
            return true;
        }).orElse(false);
    }

    public boolean updateUser(UUID id, UserUpdateDto dto) {
        return userDao.findById(id).map(user -> {
            Role role = roleService.findByName(dto.roleName)
                    .orElseThrow(() -> new IllegalArgumentException("Ungültige Rolle: " + dto.roleName));
            UserMapper.updateEntity(user, dto, role, passwordService);
            userDao.update(user);
            return true;
        }).orElse(false);
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
