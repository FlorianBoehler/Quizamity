package com.quizamity.service;

import com.quizamity.dao.GameSessionDao;
import com.quizamity.dto.GameSessionCreateDto;
import com.quizamity.dto.GameSessionResponseDto;
import com.quizamity.dto.GameSessionUpdateDto;
import com.quizamity.dao.GameDao;
import com.quizamity.dao.UserDao;
import com.quizamity.mapper.GameSessionMapper;
import com.quizamity.model.Game;
import com.quizamity.model.GameSession;
import com.quizamity.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameSessionService {

    @Inject
    private GameSessionDao gameSessionDao;

    @Inject
    private GameDao gameDao;

    @Inject
    private UserDao userDao;

    public void createGameSession(GameSessionCreateDto dto) {
        Game game = gameDao.findById(dto.gameId)
                .orElseThrow(() -> new IllegalArgumentException("Spiel nicht gefunden"));
        User user = userDao.findById(dto.userId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));

        GameSession session = GameSessionMapper.toEntity(dto, game, user);
        gameSessionDao.create(session);
    }

    public Optional<GameSessionResponseDto> getGameSession(UUID id) {
        return gameSessionDao.findById(id).map(GameSessionMapper::toDto);
    }

    public List<GameSessionResponseDto> getAllGameSessions() {
        return gameSessionDao.findAll().stream()
                .map(GameSessionMapper::toDto)
                .toList();
    }

    public List<GameSessionResponseDto> getSessionsByGame(UUID gameId) {
        return gameSessionDao.findByGameId(gameId).stream()
                .map(GameSessionMapper::toDto)
                .toList();
    }

    public List<GameSessionResponseDto> getSessionsByUser(UUID userId) {
        return gameSessionDao.findByUserId(userId).stream()
                .map(GameSessionMapper::toDto)
                .toList();
    }

    public boolean updateGameSession(UUID id, GameSessionUpdateDto dto) {
        return gameSessionDao.findById(id).map(session -> {
            GameSessionMapper.updateEntity(session, dto);
            gameSessionDao.update(session);
            return true;
        }).orElse(false);
    }

    public boolean deleteGameSession(UUID id) {
        return gameSessionDao.findById(id).map(session -> {
            gameSessionDao.delete(session);
            return true;
        }).orElse(false);
    }

}
