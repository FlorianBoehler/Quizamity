package com.quizamity.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class GameSessionCreateDto {

    @NotNull
    public UUID gameId;

    @NotNull
    public UUID userId;

    @NotNull
    public Integer score;

    @NotNull
    public Integer correctAnswers;

    @NotNull
    public Integer mistakes;
}
