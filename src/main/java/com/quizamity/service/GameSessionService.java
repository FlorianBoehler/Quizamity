package com.quizamity.service;

import com.quizamity.dao.GameSessionDao;
import com.quizamity.model.GameSession;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameSessionService {

    @Inject
    private GameSessionDao gameSessionDao;

    public void createGameSession(GameSession gameSession) {
        gameSessionDao.create(gameSession);
    }

    public Optional<GameSession> getGameSession(UUID id) {
        return gameSessionDao.findById(id);
    }

    public List<GameSession> getAllGameSessions() {
        return gameSessionDao.findAll();
    }

    public List<GameSession> getGameSessionsByGame(UUID gameId) {
        return gameSessionDao.findByGameId(gameId);
    }

    public List<GameSession> getGameSessionsByUser(UUID userId) {
        return gameSessionDao.findByUserId(userId);
    }

    public boolean updateGameSession(UUID id, GameSession updated) {
        return gameSessionDao.findById(id).map(gs -> {
            gs.setGame(updated.getGame());
            gs.setUser(updated.getUser());
            gs.setScore(updated.getScore());
            gs.setCorrectAnswers(updated.getCorrectAnswers());
            gs.setMistakes(updated.getMistakes());
            gameSessionDao.update(gs);
            return true;
        }).orElse(false);
    }

    public boolean deleteGameSession(UUID id) {
        return gameSessionDao.findById(id).map(gs -> {
            gameSessionDao.delete(gs);
            return true;
        }).orElse(false);
    }
}
