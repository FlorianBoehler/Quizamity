package com.quizamity.mapper;

import com.quizamity.dto.*;
import com.quizamity.model.Game;
import com.quizamity.model.GameParticipant;
import com.quizamity.model.User;

import java.time.LocalDateTime;

public class GameParticipantMapper {

    public static GameParticipant toEntity(GameParticipantCreateDto dto, Game game, User user) {
        return new GameParticipant(game, user, dto.score, LocalDateTime.now());
    }

    public static void updateEntity(GameParticipant participant, GameParticipantUpdateDto dto) {
        participant.setScore(dto.score);
    }

    public static GameParticipantResponseDto toDto(GameParticipant participant) {
        GameParticipantResponseDto dto = new GameParticipantResponseDto();
        dto.id = participant.getId();
        dto.gameId = participant.getGame().getId();
        dto.userId = participant.getUser().getId();
        dto.score = participant.getScore();
        dto.joinedAt = participant.getJoinedAt();
        return dto;
    }
}
