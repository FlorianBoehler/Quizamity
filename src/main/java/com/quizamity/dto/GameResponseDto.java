package com.quizamity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameResponseDto {

    public UUID id;
    public int mode;
    public String categoryName;
    public LocalDateTime createdAt;
    public LocalDateTime finishedAt;
}
