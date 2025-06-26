package com.quizamity.mapper;

import com.quizamity.dto.GameSessionCreateDto;
import com.quizamity.dto.GameSessionResponseDto;
import com.quizamity.dto.GameSessionUpdateDto;
import com.quizamity.model.Game;
import com.quizamity.model.GameSession;
import com.quizamity.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameSessionMapperTest {

    @Mock
    private Game mockGame;

    @Mock
    private User mockUser;

    @Mock
    private GameSession mockSession;

    private final UUID gameId = UUID.randomUUID();
    private final UUID userId = UUID.randomUUID();
    private final UUID sessionId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockGame.getId()).thenReturn(gameId);
        when(mockUser.getId()).thenReturn(userId);
        when(mockSession.getId()).thenReturn(sessionId);
        when(mockSession.getGame()).thenReturn(mockGame);
        when(mockSession.getUser()).thenReturn(mockUser);
    }

    @Test
    void toEntity_shouldMapDtoToEntity_whenValidInput() {
        // Arrange
        GameSessionCreateDto dto = new GameSessionCreateDto();
        dto.score = 100;
        dto.correctAnswers = 10;
        dto.mistakes = 2;

        // Act
        GameSession result = GameSessionMapper.toEntity(dto, mockGame, mockUser);

        // Assert
        assertNotNull(result);
        assertEquals(mockGame, result.getGame());
        assertEquals(mockUser, result.getUser());
        assertEquals(dto.score, result.getScore());
        assertEquals(dto.correctAnswers, result.getCorrectAnswers());
        assertEquals(dto.mistakes, result.getMistakes());
    }

    @Test
    void updateEntity_shouldUpdateFields_whenDtoIsProvided() {
        // Arrange
        GameSessionUpdateDto dto = new GameSessionUpdateDto();
        dto.score = 200;
        dto.correctAnswers = 15;
        dto.mistakes = 1;

        // Act
        GameSessionMapper.updateEntity(mockSession, dto);

        // Assert
        verify(mockSession).setScore(dto.score);
        verify(mockSession).setCorrectAnswers(dto.correctAnswers);
        verify(mockSession).setMistakes(dto.mistakes);
    }

    @Test
    void toDto_shouldMapEntityToDto_whenValidEntityProvided() {
        // Arrange
        when(mockSession.getScore()).thenReturn(150);
        when(mockSession.getCorrectAnswers()).thenReturn(12);
        when(mockSession.getMistakes()).thenReturn(3);

        // Act
        GameSessionResponseDto dto = GameSessionMapper.toDto(mockSession);

        // Assert
        assertNotNull(dto);
        assertEquals(sessionId, dto.id);
        assertEquals(gameId, dto.gameId);
        assertEquals(userId, dto.userId);
        assertEquals(150, dto.score);
        assertEquals(12, dto.correctAnswers);
        assertEquals(3, dto.mistakes);
    }
}
