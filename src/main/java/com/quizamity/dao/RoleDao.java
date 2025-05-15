package com.quizamity.dao;

import com.quizamity.model.Role;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class RoleDao {

    @PersistenceContext(unitName = "quizamityPU")
    private EntityManager em;

    public void create(Role role) {
        em.persist(role);
    }

    public Optional<Role> findById(UUID id) {
        return Optional.ofNullable(em.find(Role.class, id));
    }

    public Optional<Role> findByName(String name) {
        return em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    public List<Role> findAll() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }
}
