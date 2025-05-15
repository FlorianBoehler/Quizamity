package com.quizamity.service;

import com.quizamity.dao.GameParticipantDao;
import com.quizamity.model.GameParticipant;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameParticipantService {

    @Inject
    private GameParticipantDao dao;

    public void create(GameParticipant participant) {
        dao.create(participant);
    }

    public Optional<GameParticipant> getById(UUID id) {
        return dao.findById(id);
    }

    public List<GameParticipant> getAll() {
        return dao.findAll();
    }

    public List<GameParticipant> getByGame(UUID gameId) {
        return dao.findByGameId(gameId);
    }

    public List<GameParticipant> getByUser(UUID userId) {
        return dao.findByUserId(userId);
    }

    public boolean update(UUID id, GameParticipant updated) {
        return dao.findById(id).map(p -> {
            p.setGame(updated.getGame());
            p.setUser(updated.getUser());
            p.setScore(updated.getScore());
            p.setJoinedAt(updated.getJoinedAt());
            dao.update(p);
            return true;
        }).orElse(false);
    }

    public boolean delete(UUID id) {
        return dao.findById(id).map(p -> {
            dao.delete(p);
            return true;
        }).orElse(false);
    }
}
