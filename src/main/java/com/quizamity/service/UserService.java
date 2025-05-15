package com.quizamity.service;

import com.quizamity.dao.UserDao;
import com.quizamity.model.Role;
import com.quizamity.model.User;
import com.quizamity.security.PasswordService;  // Sicherstellen, dass das korrekt importiert ist
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private PasswordService passwordService;

    @Inject
    private RoleService roleService;

    public void createUser(User user) {
        String hashedPassword = passwordService.hash(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);
        userDao.create(user);
    }

    public Optional<User> getUser(UUID id) {
        return userDao.findById(id);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean updateUser(UUID id, User updatedUser) {
        return userDao.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());

            if (!updatedUser.getPasswordHash().isBlank()) {
                String newHashedPassword = passwordService.hash(updatedUser.getPasswordHash());
                user.setPasswordHash(newHashedPassword);
            }

            userDao.update(user);
            return true;
        }).orElse(false);
    }

    public boolean deleteUser(UUID id) {
        return userDao.findById(id).map(user -> {
            userDao.delete(user);
            return true;
        }).orElse(false);
    }
}