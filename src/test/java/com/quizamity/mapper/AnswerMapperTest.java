package com.quizamity.mapper;
import java.util.UUID;
import com.quizamity.dto.AnswerCreateDto;
import com.quizamity.dto.AnswerResponseDto;
import com.quizamity.dto.AnswerUpdateDto;
import com.quizamity.model.Answer;
import com.quizamity.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AnswerMapperTest {

    private Question mockQuestion;

    @BeforeEach
    void setUp() {
        // Create a mock Question with a specific UUID
        mockQuestion = Mockito.mock(Question.class);
        Mockito.when(mockQuestion.getId()).thenReturn(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
    }

    @Test
    void toEntity_shouldReturnCorrectAnswer_whenValidDtoAndQuestionProvided() {
        // Arrange
        AnswerCreateDto dto = new AnswerCreateDto();
        dto.text = "Paris";
        dto.isCorrect = true;

        // Act
        Answer result = AnswerMapper.toEntity(dto, mockQuestion);

        // Assert
        assertNotNull(result);
        assertEquals("Paris", result.getText());
        assertTrue(result.isCorrect());
        assertEquals(mockQuestion, result.getQuestion());
    }

    @Test
    void toDto_shouldReturnCorrectDto_whenValidAnswerProvided() {
        // Arrange
        UUID answerId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID questionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Question mockQuestion = Mockito.mock(Question.class);
        Mockito.when(mockQuestion.getId()).thenReturn(questionId);

        Answer answer = Mockito.mock(Answer.class);
        Mockito.when(answer.getId()).thenReturn(answerId);
        Mockito.when(answer.getText()).thenReturn("Paris");
        Mockito.when(answer.isCorrect()).thenReturn(true);
        Mockito.when(answer.getQuestion()).thenReturn(mockQuestion);

        // Act
        AnswerResponseDto dto = AnswerMapper.toDto(answer);

        // Assert
        assertNotNull(dto);
        assertEquals(answerId, dto.id);
        assertEquals("Paris", dto.text);
        assertTrue(dto.isCorrect);
        assertEquals(questionId, dto.questionId);
    }

    @Test
    void updateEntity_shouldUpdateAnswerFields_whenValidDtoProvided() {
        // Arrange
        Answer answer = new Answer(mockQuestion, "London", false);
        AnswerUpdateDto dto = new AnswerUpdateDto();
        dto.text = "Berlin";
        dto.isCorrect = true;

        // Act
        AnswerMapper.updateEntity(answer, dto);

        // Assert
        assertEquals("Berlin", answer.getText());
        assertTrue(answer.isCorrect());
    }
}
