package com.quizamity.dao;

import com.quizamity.model.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class CategoryDao {

    @PersistenceContext(unitName = "quizamityPU")
    private EntityManager em;

    public void create(Category category) {
        em.persist(category);
    }

    public Optional<Category> findById(UUID id) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    public Optional<Category> findByName(String name) {
        return em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public Category update(Category category) {
        return em.merge(category);
    }

    public void delete(Category category) {
        em.remove(em.contains(category) ? category : em.merge(category));
    }
}
