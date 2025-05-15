package com.quizamity.dao;

import com.quizamity.model.Game;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameDao {

    @PersistenceContext(unitName = "quizamityPU")
    private EntityManager em;

    public void create(Game game) {
        em.persist(game);
    }

    public Optional<Game> findById(UUID id) {
        return Optional.ofNullable(em.find(Game.class, id));
    }

    public List<Game> findAll() {
        return em.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }

    public Game update(Game game) {
        return em.merge(game);
    }

    public void delete(Game game) {
        em.remove(em.contains(game) ? game : em.merge(game));
    }
}
