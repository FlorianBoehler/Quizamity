package com.quizamity.dto;

import jakarta.validation.constraints.NotNull;

public class GameParticipantUpdateDto {

    @NotNull
    public Integer score;
}
