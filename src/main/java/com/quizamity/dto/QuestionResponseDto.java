package com.quizamity.dto;

import java.util.UUID;

public class QuestionResponseDto {
    public UUID id;
    public String text;
    public int difficulty;
    public String categoryName;
    public String createdByUsername;
    public boolean isApproved;
}
