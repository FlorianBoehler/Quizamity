package com.quizamity.service;

import com.quizamity.dao.GameDao;
import com.quizamity.dto.GameCreateDto;
import com.quizamity.dto.GameResponseDto;
import com.quizamity.dto.GameUpdateDto;
import com.quizamity.mapper.GameMapper;
import com.quizamity.model.Category;
import com.quizamity.model.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameDao gameDao;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private GameService gameService;

    private UUID gameId;
    private UUID categoryId;
    private Category category;
    private Game game;

    @BeforeEach
    void setUp() {
        gameId = UUID.randomUUID();
        categoryId = UUID.randomUUID();
        category = new Category();
        category.setId(categoryId);

        game = new Game();
        game.setId(gameId);
        game.setCategory(category);
    }


    @Test
    void createGame_shouldThrowException_whenCategoryNotFound() {
        GameCreateDto dto = new GameCreateDto();
        dto.categoryId = categoryId;

        when(categoryService.getCategory(categoryId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> gameService.createGame(dto));
    }


    @Test
    void getGame_shouldReturnEmpty_whenGameNotFound() {
        when(gameDao.findById(gameId)).thenReturn(Optional.empty());

        Optional<GameResponseDto> result = gameService.getGame(gameId);

        assertFalse(result.isPresent());
    }

    @Test
    void deleteGame_shouldDeleteAndReturnTrue_whenGameExists() {
        when(gameDao.findById(gameId)).thenReturn(Optional.of(game));

        boolean result = gameService.deleteGame(gameId);

        assertTrue(result);
        verify(gameDao, times(1)).delete(game);
    }

    @Test
    void deleteGame_shouldReturnFalse_whenGameNotFound() {
        when(gameDao.findById(gameId)).thenReturn(Optional.empty());

        boolean result = gameService.deleteGame(gameId);

        assertFalse(result);
        verify(gameDao, never()).delete(any());
    }



    @Test
    void updateGame_shouldThrowException_whenCategoryNotFound() {
        GameUpdateDto dto = new GameUpdateDto();
        dto.categoryId = categoryId;

        when(gameDao.findById(gameId)).thenReturn(Optional.of(game));
        when(categoryService.getCategory(categoryId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> gameService.updateGame(gameId, dto));
    }

    @Test
    void updateGame_shouldReturnFalse_whenGameNotFound() {
        GameUpdateDto dto = new GameUpdateDto();
        dto.categoryId = categoryId;

        when(gameDao.findById(gameId)).thenReturn(Optional.empty());

        boolean result = gameService.updateGame(gameId, dto);

        assertFalse(result);
        verify(gameDao, never()).update(any());
    }
}
