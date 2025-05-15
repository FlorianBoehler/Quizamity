package com.quizamity.dao;

import com.quizamity.model.GameSession;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameSessionDao {

    @PersistenceContext(unitName = "quizamityPU")
    private EntityManager em;

    public void create(GameSession gameSession) {
        em.persist(gameSession);
    }

    public Optional<GameSession> findById(UUID id) {
        return Optional.ofNullable(em.find(GameSession.class, id));
    }

    public List<GameSession> findAll() {
        return em.createQuery("SELECT gs FROM GameSession gs", GameSession.class).getResultList();
    }

    public List<GameSession> findByGameId(UUID gameId) {
        return em.createQuery("SELECT gs FROM GameSession gs WHERE gs.game.id = :gameId", GameSession.class)
                .setParameter("gameId", gameId)
                .getResultList();
    }

    public List<GameSession> findByUserId(UUID userId) {
        return em.createQuery("SELECT gs FROM GameSession gs WHERE gs.user.id = :userId", GameSession.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public GameSession update(GameSession gameSession) {
        return em.merge(gameSession);
    }

    public void delete(GameSession gameSession) {
        em.remove(em.contains(gameSession) ? gameSession : em.merge(gameSession));
    }
}
