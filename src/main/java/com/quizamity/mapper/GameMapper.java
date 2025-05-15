package com.quizamity.mapper;

import com.quizamity.dto.GameCreateDto;
import com.quizamity.dto.GameResponseDto;
import com.quizamity.dto.GameUpdateDto;
import com.quizamity.model.Category;
import com.quizamity.model.Game;

import java.time.LocalDateTime;

public class GameMapper {

    public static Game toEntity(GameCreateDto dto, Category category) {
        return new Game(dto.mode, category, LocalDateTime.now(), null);
    }

    public static GameResponseDto toDto(Game game) {
        GameResponseDto dto = new GameResponseDto();
        dto.id = game.getId();
        dto.mode = game.getMode();
        dto.categoryName = game.getCategory().getName();
        dto.createdAt = game.getCreatedAt();
        dto.finishedAt = game.getFinishedAt();
        return dto;
    }

    public static void updateEntity(Game game, GameUpdateDto dto, Category category) {
        game.setMode(dto.mode);
        game.setCategory(category);
        game.setFinishedAt(dto.finishedAt); // optional, kann null sein
    }

}
