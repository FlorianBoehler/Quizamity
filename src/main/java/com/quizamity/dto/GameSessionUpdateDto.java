package com.quizamity.dto;

import jakarta.validation.constraints.NotNull;

public class GameSessionUpdateDto {

    @NotNull
    public Integer score;

    @NotNull
    public Integer correctAnswers;

    @NotNull
    public Integer mistakes;
}
