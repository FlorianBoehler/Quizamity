package com.quizamity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameParticipantResponseDto {

    public UUID id;
    public UUID gameId;
    public UUID userId;
    public int score;
    public LocalDateTime joinedAt;
}
