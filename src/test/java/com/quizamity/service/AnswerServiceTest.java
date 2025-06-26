package com.quizamity.service;

import com.quizamity.dao.AnswerDao;
import com.quizamity.dao.QuestionDao;
import com.quizamity.dto.AnswerCreateDto;
import com.quizamity.mapper.AnswerMapper;
import com.quizamity.model.Answer;
import com.quizamity.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AnswerServiceTest {

    @InjectMocks
    private AnswerService answerService;

    @Mock
    private AnswerDao answerDao;

    @Mock
    private QuestionDao questionDao;

    private UUID questionId;
    private Question mockQuestion;

    @BeforeEach
    void setup() {
        questionId = UUID.randomUUID();
        mockQuestion = new Question();
        mockQuestion.setId(questionId);
    }

    @Test
    void testCreateAnswer_successful() {
        // Arrange
        AnswerCreateDto dto = new AnswerCreateDto();
        dto.questionId = questionId;
        dto.text = "Sample Answer";
        dto.isCorrect = true;

        // Stub: Wenn questionDao.findById(...) aufgerufen wird, soll mockQuestion zurückgegeben werden
        when(questionDao.findById(questionId)).thenReturn(Optional.of(mockQuestion));

        // Stub Mapper (statische Methode)
        try (MockedStatic<AnswerMapper> mapperMock = mockStatic(AnswerMapper.class)) {
            Answer answer = new Answer();
            mapperMock.when(() -> AnswerMapper.toEntity(dto, mockQuestion)).thenReturn(answer);

            // Act
            answerService.createAnswer(dto);

            // Assert: Überprüfe, ob answerDao.create() mit dem erzeugten Answer aufgerufen wurde
            verify(answerDao).create(answer);
        }
    }

    @Test
    void testCreateAnswer_questionNotFound_throwsException() {
        // Arrange
        AnswerCreateDto dto = new AnswerCreateDto();
        dto.questionId = UUID.randomUUID();

        when(questionDao.findById(dto.questionId)).thenReturn(Optional.empty());

        // Act + Assert
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            answerService.createAnswer(dto);
        });

        verify(answerDao, never()).create(any());
    }
}
