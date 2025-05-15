package com.quizamity.mapper;

import com.quizamity.dto.QuestionCreateDto;
import com.quizamity.dto.QuestionResponseDto;
import com.quizamity.dto.QuestionUpdateDto;
import com.quizamity.model.Category;
import com.quizamity.model.Question;
import com.quizamity.model.User;

public class QuestionMapper {

    public static Question toEntity(QuestionCreateDto dto, Category category, User createdBy) {
        return new Question(
                dto.text,
                dto.difficulty,
                category,
                createdBy,
                null,
                false
        );
    }

    public static QuestionResponseDto toDto(Question question) {
        QuestionResponseDto dto = new QuestionResponseDto();
        dto.id = question.getId();
        dto.text = question.getText();
        dto.difficulty = question.getDifficulty();
        dto.categoryName = question.getCategory().getName();
        dto.createdByUsername = question.getCreatedBy().getUsername();
        dto.isApproved = question.isApproved();
        return dto;
    }

    public static void updateEntity(Question question, QuestionUpdateDto dto, Category category) {
        question.setText(dto.text);
        question.setDifficulty(dto.difficulty);
        question.setCategory(category);
        if (dto.isApproved != null) {
            question.setApproved(dto.isApproved);
        }
    }

}
