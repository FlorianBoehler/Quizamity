package com.quizamity.mapper;

import com.quizamity.dto.QuestionCreateDto;
import com.quizamity.dto.QuestionResponseDto;
import com.quizamity.dto.QuestionUpdateDto;
import com.quizamity.model.Category;
import com.quizamity.model.Question;
import com.quizamity.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionMapperTest {

    private Category category;
    private User user;

    @BeforeEach
    void setUp() {
        // Mocking Category and User
        category = mock(Category.class);
        when(category.getName()).thenReturn("Science");

        user = mock(User.class);
        when(user.getUsername()).thenReturn("testuser");
    }

    @Test
    void toEntity_shouldMapFieldsCorrectly_whenValidInput() {
        // Given
        QuestionCreateDto dto = new QuestionCreateDto();
        dto.text = "What is the capital of France?";
        dto.difficulty = 2;

        // When
        Question result = QuestionMapper.toEntity(dto, category, user);

        // Then
        assertNotNull(result);
        assertEquals("What is the capital of France?", result.getText());
        assertEquals(2, result.getDifficulty());
        assertEquals(category, result.getCategory());
        assertEquals(user, result.getCreatedBy());
        assertFalse(result.isApproved());
    }

    @Test
    void toDto_shouldMapFieldsCorrectly_whenValidQuestion() {
        // Given
        UUID questionId = UUID.randomUUID();
        Question question = mock(Question.class);
        when(question.getId()).thenReturn(questionId);
        when(question.getText()).thenReturn("What is 2+2?");
        when(question.getDifficulty()).thenReturn(1);
        when(question.getCategory()).thenReturn(category);
        when(question.getCreatedBy()).thenReturn(user);
        when(question.isApproved()).thenReturn(true);

        // When
        QuestionResponseDto dto = QuestionMapper.toDto(question);

        // Then
        assertNotNull(dto);
        assertEquals(questionId, dto.id);
        assertEquals("What is 2+2?", dto.text);
        assertEquals(1, dto.difficulty);
        assertEquals("Science", dto.categoryName);
        assertEquals("testuser", dto.createdByUsername);
        assertTrue(dto.isApproved);
    }

    @Test
    void updateEntity_shouldUpdateAllFields_whenDtoHasAllFields() {
        // Given
        Question question = mock(Question.class);

        QuestionUpdateDto dto = new QuestionUpdateDto();
        dto.text = "Updated text";
        dto.difficulty = 3;
        dto.isApproved = true;

        // When
        QuestionMapper.updateEntity(question, dto, category);

        // Then
        verify(question).setText("Updated text");
        verify(question).setDifficulty(3);
        verify(question).setCategory(category);
        verify(question).setApproved(true);
    }

    @Test
    void updateEntity_shouldNotUpdateApproval_whenIsApprovedIsNull() {
        // Given
        Question question = mock(Question.class);

        QuestionUpdateDto dto = new QuestionUpdateDto();
        dto.text = "Another question";
        dto.difficulty = 5;
        dto.isApproved = null;

        // When
        QuestionMapper.updateEntity(question, dto, category);

        // Then
        verify(question).setText("Another question");
        verify(question).setDifficulty(5);
        verify(question).setCategory(category);
        verify(question, never()).setApproved(anyBoolean());
    }
}
