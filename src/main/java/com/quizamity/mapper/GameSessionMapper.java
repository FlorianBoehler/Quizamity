package com.quizamity.mapper;

import com.quizamity.dto.*;
import com.quizamity.model.Game;
import com.quizamity.model.GameSession;
import com.quizamity.model.User;

import java.time.LocalDateTime;

public class GameSessionMapper {

    public static GameSession toEntity(GameSessionCreateDto dto, Game game, User user) {
        return new GameSession(game, user, dto.score, dto.correctAnswers, dto.mistakes);
    }

    public static void updateEntity(GameSession session, GameSessionUpdateDto dto) {
        session.setScore(dto.score);
        session.setCorrectAnswers(dto.correctAnswers);
        session.setMistakes(dto.mistakes);
    }

    public static GameSessionResponseDto toDto(GameSession session) {
        GameSessionResponseDto dto = new GameSessionResponseDto();
        dto.id = session.getId();
        dto.gameId = session.getGame().getId();
        dto.userId = session.getUser().getId();
        dto.score = session.getScore();
        dto.correctAnswers = session.getCorrectAnswers();
        dto.mistakes = session.getMistakes();
        return dto;
    }
}
