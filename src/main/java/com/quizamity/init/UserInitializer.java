package com.quizamity.init;

import com.quizamity.model.Role;
import com.quizamity.model.User;
import com.quizamity.service.RoleService;
import com.quizamity.service.UserService;
import com.quizamity.security.PasswordService;
import com.quizamity.dao.UserDao;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.DependsOn;
import jakarta.inject.Inject;

@Startup
@Singleton
@DependsOn("RoleInitializer")
public class UserInitializer {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private PasswordService passwordService;

    @Inject
    private UserDao userDao;

    @PostConstruct
    public void init() {
        createUserIfNotExists(
                "admin",
                "adminpass",
                "admin@example.com",
                "ADMIN"
        );

        createUserIfNotExists(
                "moderator",
                "modpass",
                "moderator@example.com",
                "MODERATOR"
        );

        createUserIfNotExists(
                "student",
                "studentpass",
                "student@example.com",
                "STUDENT"
        );
    }

    private void createUserIfNotExists(String username, String plainPassword, String email, String roleName) {
        if (userService.findByUsername(username).isEmpty()) {
            Role role = roleService.findByName(roleName)
                    .orElseThrow(() -> new IllegalStateException("Role " + roleName + " not found"));

            String hashedPassword = passwordService.hash(plainPassword);

            User user = new User(username, hashedPassword, email, role);
            userDao.create(user);
        }
    }
}
