package com.quizamity.service;

import com.quizamity.dao.GameParticipantDao;
import com.quizamity.dto.GameParticipantCreateDto;
import com.quizamity.dto.GameParticipantResponseDto;
import com.quizamity.dto.GameParticipantUpdateDto;
import com.quizamity.mapper.GameParticipantMapper;
import com.quizamity.model.Game;
import com.quizamity.model.GameParticipant;
import com.quizamity.model.User;
import com.quizamity.dao.GameDao;
import com.quizamity.dao.UserDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameParticipantService {

    @Inject
    private GameParticipantDao gameParticipantDao;

    @Inject
    private GameDao gameDao;

    @Inject
    private UserDao userDao;

    public void createParticipant(GameParticipantCreateDto dto) {
        Game game = gameDao.findById(dto.gameId)
                .orElseThrow(() -> new IllegalArgumentException("Spiel nicht gefunden"));
        User user = userDao.findById(dto.userId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));

        GameParticipant participant = GameParticipantMapper.toEntity(dto, game, user);
        gameParticipantDao.create(participant);
    }

    public Optional<GameParticipantResponseDto> getById(UUID id) {
        return gameParticipantDao.findById(id)
                .map(GameParticipantMapper::toDto);
    }

    public List<GameParticipantResponseDto> getAll() {
        return gameParticipantDao.findAll().stream()
                .map(GameParticipantMapper::toDto)
                .toList();
    }

    public List<GameParticipantResponseDto> getByGame(UUID gameId) {
        return gameParticipantDao.findByGameId(gameId).stream()
                .map(GameParticipantMapper::toDto)
                .toList();
    }

    public List<GameParticipantResponseDto> getByUser(UUID userId) {
        return gameParticipantDao.findByUserId(userId).stream()
                .map(GameParticipantMapper::toDto)
                .toList();
    }

    public boolean update(UUID id, GameParticipantUpdateDto dto) {
        return gameParticipantDao.findById(id).map(p -> {
            GameParticipantMapper.updateEntity(p, dto);
            gameParticipantDao.update(p);
            return true;
        }).orElse(false);
    }

    public boolean delete(UUID id) {
        return gameParticipantDao.findById(id).map(p -> {
            gameParticipantDao.delete(p);
            return true;
        }).orElse(false);
    }

}
