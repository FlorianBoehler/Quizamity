package com.quizamity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameSessionResponseDto {

    public UUID id;
    public UUID gameId;
    public UUID userId;
    public int score;
    public int correctAnswers;
    public int mistakes;
    public LocalDateTime joinedAt;
}
