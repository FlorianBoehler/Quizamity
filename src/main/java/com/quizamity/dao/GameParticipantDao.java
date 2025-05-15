package com.quizamity.dao;

import com.quizamity.model.GameParticipant;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameParticipantDao {

    @PersistenceContext(unitName = "quizamityPU")
    private EntityManager em;

    public void create(GameParticipant participant) {
        em.persist(participant);
    }

    public Optional<GameParticipant> findById(UUID id) {
        return Optional.ofNullable(em.find(GameParticipant.class, id));
    }

    public List<GameParticipant> findAll() {
        return em.createQuery("SELECT gp FROM GameParticipant gp", GameParticipant.class).getResultList();
    }

    public List<GameParticipant> findByGameId(UUID gameId) {
        return em.createQuery("SELECT gp FROM GameParticipant gp WHERE gp.game.id = :gameId", GameParticipant.class)
                .setParameter("gameId", gameId)
                .getResultList();
    }

    public List<GameParticipant> findByUserId(UUID userId) {
        return em.createQuery("SELECT gp FROM GameParticipant gp WHERE gp.user.id = :userId", GameParticipant.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public GameParticipant update(GameParticipant participant) {
        return em.merge(participant);
    }

    public void delete(GameParticipant participant) {
        em.remove(em.contains(participant) ? participant : em.merge(participant));
    }
}
