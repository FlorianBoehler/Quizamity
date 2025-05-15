package com.quizamity.service;

import com.quizamity.dao.GameDao;
import com.quizamity.model.Game;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameService {

    @Inject
    private GameDao gameDao;

    public void createGame(Game game) {
        gameDao.create(game);
    }

    public Optional<Game> getGame(UUID id) {
        return gameDao.findById(id);
    }

    public List<Game> getAllGames() {
        return gameDao.findAll();
    }

    public boolean updateGame(UUID id, Game updated) {
        return gameDao.findById(id).map(game -> {
            game.setMode(updated.getMode());
            game.setCategory(updated.getCategory());
            game.setCreatedAt(updated.getCreatedAt());
            game.setFinishedAt(updated.getFinishedAt());
            gameDao.update(game);
            return true;
        }).orElse(false);
    }

    public boolean deleteGame(UUID id) {
        return gameDao.findById(id).map(game -> {
            gameDao.delete(game);
            return true;
        }).orElse(false);
    }
}
