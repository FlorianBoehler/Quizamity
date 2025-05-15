package com.quizamity.service;

import com.quizamity.dao.RoleDao;
import com.quizamity.model.Role;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class RoleService {

    @Inject
    private RoleDao roleDao;

    public void createRole(Role role) {
        roleDao.create(role);
    }

    public Optional<Role> getRole(UUID id) {
        return roleDao.findById(id);
    }

    public Optional<Role> findByName(String name) {
        return roleDao.findByName(name);
    }

    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }
}
