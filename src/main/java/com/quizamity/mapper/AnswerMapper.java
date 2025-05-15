package com.quizamity.mapper;

import com.quizamity.dto.AnswerCreateDto;
import com.quizamity.dto.AnswerResponseDto;
import com.quizamity.dto.AnswerUpdateDto;
import com.quizamity.model.Answer;
import com.quizamity.model.Question;

public class AnswerMapper {

    public static Answer toEntity(AnswerCreateDto dto, Question question) {
        return new Answer(question, dto.text, dto.isCorrect);
    }

    public static AnswerResponseDto toDto(Answer answer) {
        AnswerResponseDto dto = new AnswerResponseDto();
        dto.id = answer.getId();
        dto.text = answer.getText();
        dto.isCorrect = answer.isCorrect();
        dto.questionId = answer.getQuestion().getId();
        return dto;
    }

    public static void updateEntity(Answer answer, AnswerUpdateDto dto) {
        answer.setText(dto.text);
        answer.setCorrect(dto.isCorrect);
    }

}
