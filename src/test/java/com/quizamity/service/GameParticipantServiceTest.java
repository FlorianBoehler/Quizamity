package com.quizamity.service;

import com.quizamity.dao.GameDao;
import com.quizamity.dao.GameParticipantDao;
import com.quizamity.dao.UserDao;
import com.quizamity.dto.GameParticipantCreateDto;
import com.quizamity.dto.GameParticipantResponseDto;
import com.quizamity.dto.GameParticipantUpdateDto;
import com.quizamity.mapper.GameParticipantMapper;
import com.quizamity.model.Game;
import com.quizamity.model.GameParticipant;
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
class GameParticipantServiceTest {

    @Mock
    private GameParticipantDao gameParticipantDao;

    @Mock
    private GameDao gameDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private GameParticipantService service;

    private UUID gameId;
    private UUID userId;
    private UUID participantId;
    private Game game;
    private User user;
    private GameParticipant participant;

    @BeforeEach
    void setUp() {
        gameId = UUID.randomUUID();
        userId = UUID.randomUUID();
        participantId = UUID.randomUUID();

        game = new Game();
        game.setId(gameId);

        user = new User();
        user.setId(userId);

        participant = new GameParticipant();
        participant.setId(participantId);
        participant.setGame(game);
        participant.setUser(user);
    }

    @Test
    void createParticipant_shouldCreateParticipant_whenValidInput() {
        GameParticipantCreateDto dto = new GameParticipantCreateDto();
        dto.gameId = gameId;
        dto.userId = userId;

        when(gameDao.findById(gameId)).thenReturn(Optional.of(game));
        when(userDao.findById(userId)).thenReturn(Optional.of(user));

        GameParticipant mockEntity = new GameParticipant();
        mockEntity.setGame(game);
        mockEntity.setUser(user);

        try (var mocked = mockStatic(GameParticipantMapper.class)) {
            mocked.when(() -> GameParticipantMapper.toEntity(dto, game, user)).thenReturn(mockEntity);

            service.createParticipant(dto);

            verify(gameParticipantDao).create(mockEntity);
        }
    }

    @Test
    void createParticipant_shouldThrowException_whenGameNotFound() {
        GameParticipantCreateDto dto = new GameParticipantCreateDto();
        dto.gameId = gameId;
        dto.userId = userId;

        when(gameDao.findById(gameId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.createParticipant(dto));
    }

    @Test
    void createParticipant_shouldThrowException_whenUserNotFound() {
        GameParticipantCreateDto dto = new GameParticipantCreateDto();
        dto.gameId = gameId;
        dto.userId = userId;

        when(gameDao.findById(gameId)).thenReturn(Optional.of(game));
        when(userDao.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.createParticipant(dto));
    }

    @Test
    void getById_shouldReturnDto_whenParticipantExists() {
        when(gameParticipantDao.findById(participantId)).thenReturn(Optional.of(participant));

        GameParticipantResponseDto dto = new GameParticipantResponseDto();
        try (var mocked = mockStatic(GameParticipantMapper.class)) {
            mocked.when(() -> GameParticipantMapper.toDto(participant)).thenReturn(dto);

            Optional<GameParticipantResponseDto> result = service.getById(participantId);

            assertTrue(result.isPresent());
            assertEquals(dto, result.get());
        }
    }

    @Test
    void getById_shouldReturnEmpty_whenNotFound() {
        when(gameParticipantDao.findById(participantId)).thenReturn(Optional.empty());

        Optional<GameParticipantResponseDto> result = service.getById(participantId);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAll_shouldReturnAllDtos() {
        List<GameParticipant> participants = List.of(participant);
        GameParticipantResponseDto dto = new GameParticipantResponseDto();

        when(gameParticipantDao.findAll()).thenReturn(participants);

        try (var mocked = mockStatic(GameParticipantMapper.class)) {
            mocked.when(() -> GameParticipantMapper.toDto(participant)).thenReturn(dto);

            List<GameParticipantResponseDto> result = service.getAll();

            assertEquals(1, result.size());
            assertEquals(dto, result.get(0));
        }
    }

    @Test
    void getByGame_shouldReturnDtosForGame() {
        List<GameParticipant> participants = List.of(participant);
        GameParticipantResponseDto dto = new GameParticipantResponseDto();

        when(gameParticipantDao.findByGameId(gameId)).thenReturn(participants);

        try (var mocked = mockStatic(GameParticipantMapper.class)) {
            mocked.when(() -> GameParticipantMapper.toDto(participant)).thenReturn(dto);

            List<GameParticipantResponseDto> result = service.getByGame(gameId);

            assertEquals(1, result.size());
            assertEquals(dto, result.get(0));
        }
    }

    @Test
    void getByUser_shouldReturnDtosForUser() {
        List<GameParticipant> participants = List.of(participant);
        GameParticipantResponseDto dto = new GameParticipantResponseDto();

        when(gameParticipantDao.findByUserId(userId)).thenReturn(participants);

        try (var mocked = mockStatic(GameParticipantMapper.class)) {
            mocked.when(() -> GameParticipantMapper.toDto(participant)).thenReturn(dto);

            List<GameParticipantResponseDto> result = service.getByUser(userId);

            assertEquals(1, result.size());
            assertEquals(dto, result.get(0));
        }
    }

    @Test
    void update_shouldReturnFalse_whenNotFound() {
        GameParticipantUpdateDto updateDto = new GameParticipantUpdateDto();
        when(gameParticipantDao.findById(participantId)).thenReturn(Optional.empty());

        boolean result = service.update(participantId, updateDto);

        assertFalse(result);
    }

    @Test
    void delete_shouldDeleteEntity_whenFound() {
        when(gameParticipantDao.findById(participantId)).thenReturn(Optional.of(participant));

        boolean result = service.delete(participantId);

        assertTrue(result);
        verify(gameParticipantDao).delete(participant);
    }

    @Test
    void delete_shouldReturnFalse_whenNotFound() {
        when(gameParticipantDao.findById(participantId)).thenReturn(Optional.empty());

        boolean result = service.delete(participantId);

        assertFalse(result);
    }
}
