package com.quizamity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AnswerCreateDto {

    @NotNull
    public UUID questionId;

    @NotBlank
    public String text;

    @NotNull
    public Boolean isCorrect;
}
