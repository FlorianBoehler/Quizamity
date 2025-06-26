package com.quizamity.mapper;

import com.quizamity.dto.GameCreateDto;
import com.quizamity.dto.GameResponseDto;
import com.quizamity.dto.GameUpdateDto;
import com.quizamity.model.Category;
import com.quizamity.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameMapperTest {

    private Category categoryMock;

    @BeforeEach
    void setUp() {
        // Create a mock Category object used for mapping
        categoryMock = mock(Category.class);
        when(categoryMock.getName()).thenReturn("Science");
    }

    @Test
    void toEntity_shouldMapFieldsCorrectly_whenValidInputGiven() {
        // Given
        GameCreateDto dto = new GameCreateDto();
        dto.mode = 1;

        // When
        Game game = GameMapper.toEntity(dto, categoryMock);

        // Then
        assertNotNull(game, "Game object should not be null");
        assertEquals(1, game.getMode(), "Mode should be mapped correctly");
        assertEquals(categoryMock, game.getCategory(), "Category should be mapped correctly");
        assertNotNull(game.getCreatedAt(), "CreatedAt should be set to current time");
        assertNull(game.getFinishedAt(), "FinishedAt should initially be null");
    }

    @Test
    void toDto_shouldMapFieldsCorrectly_whenValidGameGiven() {
        // Given
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime finishedAt = LocalDateTime.now();

        Category category = mock(Category.class);
        when(category.getName()).thenReturn("History");

        Game game = mock(Game.class);
        when(game.getId()).thenReturn(id);
        when(game.getCategory()).thenReturn(category);
        when(game.getCreatedAt()).thenReturn(createdAt);
        when(game.getFinishedAt()).thenReturn(finishedAt);

        // When
        GameResponseDto dto = GameMapper.toDto(game);

        // Then
        assertNotNull(dto, "DTO should not be null");
        assertEquals(id, dto.id, "ID should match");
        assertEquals(0 , dto.mode, "Mode should match");
        assertEquals("History", dto.categoryName, "Category name should be mapped");
        assertEquals(createdAt, dto.createdAt, "CreatedAt should match");
        assertEquals(finishedAt, dto.finishedAt, "FinishedAt should match");
    }

    @Test
    void updateEntity_shouldUpdateGameFields_whenDtoHasNewValues() {
        // Given
        Game game = mock(Game.class);

        GameUpdateDto dto = new GameUpdateDto();
        dto.mode = 2;
        dto.finishedAt = LocalDateTime.now();

        Category newCategory = mock(Category.class);

        // When
        GameMapper.updateEntity(game, dto, newCategory);

        // Then
        // Verify that the game setters were called with the new values
        verify(game).setMode(2);
        verify(game).setCategory(newCategory);
        verify(game).setFinishedAt(dto.finishedAt);
    }

    @Test
    void updateEntity_shouldAllowNullFinishedAt_whenDtoOmitsIt() {
        // Given
        Game game = mock(Game.class);

        GameUpdateDto dto = new GameUpdateDto();
        dto.mode = 2;
        dto.finishedAt = null;

        // When
        GameMapper.updateEntity(game, dto, categoryMock);

        // Then
        verify(game).setMode(2);
        verify(game).setCategory(categoryMock);
        verify(game).setFinishedAt(null); // null is a valid case
    }
}
