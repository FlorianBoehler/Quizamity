package com.quizamity.service;

import com.quizamity.dao.QuestionDao;
import com.quizamity.dto.QuestionCreateDto;
import com.quizamity.dto.QuestionResponseDto;
import com.quizamity.dto.QuestionUpdateDto;
import com.quizamity.mapper.QuestionMapper;
import com.quizamity.model.Category;
import com.quizamity.model.Question;
import com.quizamity.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private CategoryService categoryService;

    @Mock
    private UserService userService;

    @InjectMocks
    private QuestionService questionService;

    private UUID questionId;
    private UUID categoryId;
    private UUID userId;

    @BeforeEach
    void setUp() {
        questionId = UUID.randomUUID();
        categoryId = UUID.randomUUID();
        userId = UUID.randomUUID();
    }

    @Test
    void createQuestion_shouldThrowException_whenCategoryNotFound() {
        // Arrange
        QuestionCreateDto dto = new QuestionCreateDto();
        dto.categoryId = categoryId;
        dto.createdByUserId = userId;

        when(categoryService.getCategory(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionService.createQuestion(dto));
    }


    @Test
    void updateQuestion_shouldReturnFalse_whenQuestionNotFound() {
        // Arrange
        QuestionUpdateDto dto = new QuestionUpdateDto();

        when(questionDao.findById(questionId)).thenReturn(Optional.empty());

        // Act
        boolean result = questionService.updateQuestion(questionId, dto);

        // Assert
        assertFalse(result);
    }

    @Test
    void getQuestion_shouldReturnDto_whenQuestionExists() {
        // Arrange
        Question question = new Question();

        when(questionDao.findById(questionId)).thenReturn(Optional.of(question));
        QuestionResponseDto expectedDto = new QuestionResponseDto();
        mockStaticMapper(question, expectedDto);

        // Act
        Optional<QuestionResponseDto> result = questionService.getQuestion(questionId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedDto, result.get());
    }

    @Test
    void getQuestion_shouldReturnEmpty_whenQuestionDoesNotExist() {
        // Arrange
        when(questionDao.findById(questionId)).thenReturn(Optional.empty());

        // Act
        Optional<QuestionResponseDto> result = questionService.getQuestion(questionId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void deleteQuestion_shouldDeleteAndReturnTrue_whenQuestionExists() {
        // Arrange
        Question question = new Question();
        when(questionDao.findById(questionId)).thenReturn(Optional.of(question));

        // Act
        boolean result = questionService.deleteQuestion(questionId);

        // Assert
        assertTrue(result);
        verify(questionDao).delete(question);
    }

    @Test
    void deleteQuestion_shouldReturnFalse_whenQuestionNotFound() {
        // Arrange
        when(questionDao.findById(questionId)).thenReturn(Optional.empty());

        // Act
        boolean result = questionService.deleteQuestion(questionId);

        // Assert
        assertFalse(result);
    }

    @Test
    void findByText_shouldReturnQuestion_whenExists() {
        // Arrange
        String text = "example";
        Question question = new Question();
        when(questionDao.findByText(text)).thenReturn(Optional.of(question));

        // Act
        Optional<Question> result = questionService.findByText(text);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void createDirect_shouldCallDaoCreate() {
        // Arrange
        Question question = new Question();

        // Act
        questionService.createDirect(question);

        // Assert
        verify(questionDao).create(question);
    }

    private MockedStatic<QuestionMapper> mockStaticMapper(Question question, QuestionResponseDto dto) {
        MockedStatic<QuestionMapper> mockedStatic = mockStatic(QuestionMapper.class);
        mockedStatic.when(() -> QuestionMapper.toDto(question)).thenReturn(dto);
        return mockedStatic; // Caller must close it in a try-with-resources
    }

    private MockedStatic<QuestionMapper> mockStaticMapperList(List<Question> questions, List<QuestionResponseDto> dtos) {
        MockedStatic<QuestionMapper> mockedStatic = mockStatic(QuestionMapper.class);

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            QuestionResponseDto dto = dtos.get(i);
            mockedStatic.when(() -> QuestionMapper.toDto(q)).thenReturn(dto);
        }

        return mockedStatic; // Caller must close it
    }



}
