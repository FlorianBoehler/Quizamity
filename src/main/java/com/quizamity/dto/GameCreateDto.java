package com.quizamity.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class GameCreateDto {

    @NotNull
    public Integer mode;

    @NotNull
    public UUID categoryId;
}
