package com.quizamity.init;

import com.quizamity.model.Role;
import com.quizamity.service.RoleService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.List;

@Startup
@Singleton
public class RoleInitializer {

    @Inject
    private RoleService roleService;

    @PostConstruct
    public void init() {
        List<String> standardRoles = List.of("STUDENT", "MODERATOR", "ADMIN");

        for (String roleName : standardRoles) {
            roleService.findByName(roleName).orElseGet(() -> {
                Role r = new Role(roleName);
                roleService.createRole(r);
                return r;
            });
        }
    }
}
