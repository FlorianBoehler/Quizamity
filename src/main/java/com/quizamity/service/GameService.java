package com.quizamity.service;

import com.quizamity.dao.GameDao;
import com.quizamity.dto.GameCreateDto;
import com.quizamity.dto.GameResponseDto;
import com.quizamity.dto.GameUpdateDto;
import com.quizamity.mapper.GameMapper;
import com.quizamity.model.Category;
import com.quizamity.model.Game;
import com.quizamity.service.CategoryService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class GameService {

    @Inject
    private GameDao gameDao;

    @Inject
    private CategoryService categoryService;

    public void createGame(GameCreateDto dto) {
        Category category = categoryService.getCategory(dto.categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Kategorie nicht gefunden"));

        Game game = GameMapper.toEntity(dto, category);
        gameDao.create(game);
    }

    public Optional<GameResponseDto> getGame(UUID id) {
        return gameDao.findById(id).map(GameMapper::toDto);
    }

    public List<GameResponseDto> getAllGames() {
        return gameDao.findAll().stream()
                .map(GameMapper::toDto)
                .toList();
    }

    public boolean deleteGame(UUID id) {
        return gameDao.findById(id).map(game -> {
            gameDao.delete(game);
            return true;
        }).orElse(false);
    }

    public boolean updateGame(UUID id, GameUpdateDto dto) {
        return gameDao.findById(id).map(game -> {
            Category category = categoryService.getCategory(dto.categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Kategorie nicht gefunden"));

            GameMapper.updateEntity(game, dto, category);
            gameDao.update(game);
            return true;
        }).orElse(false);
    }

}
