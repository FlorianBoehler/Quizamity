package com.quizamity.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameUpdateDto {

    @NotNull
    public Integer mode;

    @NotNull
    public UUID categoryId;

    public LocalDateTime finishedAt;
}
