package com.quizamity.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class GameParticipantCreateDto {

    @NotNull
    public UUID gameId;

    @NotNull
    public UUID userId;

    @NotNull
    public Integer score;
}
