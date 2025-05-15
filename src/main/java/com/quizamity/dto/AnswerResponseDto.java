package com.quizamity.dto;

import java.util.UUID;

public class AnswerResponseDto {

    public UUID id;
    public String text;
    public boolean isCorrect;
    public UUID questionId;
}
