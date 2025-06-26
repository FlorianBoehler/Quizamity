package com.quizamity.mapper;

import com.quizamity.dto.GameParticipantCreateDto;
import com.quizamity.dto.GameParticipantResponseDto;
import com.quizamity.dto.GameParticipantUpdateDto;
import com.quizamity.model.Game;
import com.quizamity.model.GameParticipant;
import com.quizamity.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameParticipantMapperTest {

    @Mock
    private Game game;

    @Mock
    private User user;

    private final UUID gameId = UUID.randomUUID();
    private final UUID userId = UUID.randomUUID();
    private final UUID participantId = UUID.randomUUID();



    @Test
    void toEntity_shouldReturnGameParticipant_whenValidInputProvided() {
        // Arrange
        GameParticipantCreateDto dto = new GameParticipantCreateDto();
        dto.score = 50;

        // Act
        GameParticipant result = GameParticipantMapper.toEntity(dto, game, user);

        // Assert
        assertNotNull(result, "GameParticipant should not be null");
        assertEquals(game, result.getGame(), "Game should match input");
        assertEquals(user, result.getUser(), "User should match input");
        assertEquals(dto.score, result.getScore(), "Score should be set from DTO");
        assertNotNull(result.getJoinedAt(), "JoinedAt should be set to current time");
    }

    @Test
    void updateEntity_shouldUpdateScore_whenDtoHasNewScore() {
        // Arrange
        GameParticipantUpdateDto dto = new GameParticipantUpdateDto();
        dto.score = 75;

        GameParticipant participant = new GameParticipant(game, user, 50, LocalDateTime.now());

        // Act
        GameParticipantMapper.updateEntity(participant, dto);

        // Assert
        assertEquals(dto.score, participant.getScore(), "Score should be updated to value from DTO");
    }
}

