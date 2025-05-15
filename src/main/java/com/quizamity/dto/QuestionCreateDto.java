package com.quizamity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class QuestionCreateDto {

    @NotBlank
    public String text;

    @NotNull
    public Integer difficulty;

    @NotNull
    public UUID categoryId;

    @NotNull
    public UUID createdByUserId;
}
