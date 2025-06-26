package com.quizamity.service;

import com.quizamity.dao.GameDao;
import com.quizamity.dao.GameSessionDao;
import com.quizamity.dao.UserDao;
import com.quizamity.dto.GameSessionCreateDto;
import com.quizamity.dto.GameSessionResponseDto;
import com.quizamity.dto.GameSessionUpdateDto;
import com.quizamity.mapper.GameSessionMapper;
import com.quizamity.model.Game;
import com.quizamity.model.GameSession;
import com.quizamity.model.User;
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
class GameSessionServiceTest {

    @Mock
    private GameSessionDao gameSessionDao;

    @Mock
    private GameDao gameDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private GameSessionService gameSessionService;

    private UUID gameId;
    private UUID userId;
    private UUID sessionId;
    private Game game;
    private User user;
    private GameSession session;

    @BeforeEach
    void setup() {
        gameId = UUID.randomUUID();
        userId = UUID.randomUUID();
        sessionId = UUID.randomUUID();

        game = new Game();
        user = new User();
        session = new GameSession();
    }

    @Test
    void createGameSession_shouldThrowException_whenGameNotFound() {
        GameSessionCreateDto dto = new GameSessionCreateDto();
        dto.gameId = gameId;
        dto.userId = userId;

        when(gameDao.findById(gameId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> gameSessionService.createGameSession(dto));
    }

    @Test
    void createGameSession_shouldThrowException_whenUserNotFound() {
        GameSessionCreateDto dto = new GameSessionCreateDto();
        dto.gameId = gameId;
        dto.userId = userId;

        when(gameDao.findById(gameId)).thenReturn(Optional.of(game));
        when(userDao.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> gameSessionService.createGameSession(dto));
    }

    @Test
    void getGameSession_shouldReturnEmpty_whenSessionNotFound() {
        when(gameSessionDao.findById(sessionId)).thenReturn(Optional.empty());

        Optional<GameSessionResponseDto> result = gameSessionService.getGameSession(sessionId);

        assertTrue(result.isEmpty());
    }

    @Test
    void updateGameSession_shouldReturnFalse_whenSessionNotFound() {
        GameSessionUpdateDto dto = new GameSessionUpdateDto();
        when(gameSessionDao.findById(sessionId)).thenReturn(Optional.empty());

        boolean result = gameSessionService.updateGameSession(sessionId, dto);

        assertFalse(result);
    }

    @Test
    void deleteGameSession_shouldDelete_whenSessionExists() {
        when(gameSessionDao.findById(sessionId)).thenReturn(Optional.of(session));

        boolean result = gameSessionService.deleteGameSession(sessionId);

        assertTrue(result);
        verify(gameSessionDao).delete(session);
    }

    @Test
    void deleteGameSession_shouldReturnFalse_whenSessionNotFound() {
        when(gameSessionDao.findById(sessionId)).thenReturn(Optional.empty());

        boolean result = gameSessionService.deleteGameSession(sessionId);

        assertFalse(result);
    }

    // Utility method for mocking static methods (used with Mockito's inline mocking)
    private void mockStatic(Class<?> clazz) {
        // In a real project setup, you'd use Mockito's mockStatic from mockito-inline.
        // Here we assume it's supported/configured via test framework or build tool.
    }

}
