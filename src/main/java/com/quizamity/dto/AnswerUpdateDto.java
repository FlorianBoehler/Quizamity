package com.quizamity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnswerUpdateDto {

    @NotBlank
    public String text;

    @NotNull
    public Boolean isCorrect;
}
