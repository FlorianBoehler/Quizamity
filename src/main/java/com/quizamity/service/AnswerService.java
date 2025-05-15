package com.quizamity.service;

import com.quizamity.dao.AnswerDao;
import com.quizamity.dao.QuestionDao;
import com.quizamity.dto.AnswerCreateDto;
import com.quizamity.dto.AnswerResponseDto;
import com.quizamity.dto.AnswerUpdateDto;
import com.quizamity.mapper.AnswerMapper;
import com.quizamity.model.Answer;
import com.quizamity.model.Question;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class AnswerService {

    @Inject
    private AnswerDao answerDao;

    @Inject
    private QuestionDao questionDao;

    public void createAnswer(AnswerCreateDto dto) {
        Question question = questionDao.findById(dto.questionId)
                .orElseThrow(() -> new IllegalArgumentException("Frage nicht gefunden"));

        Answer answer = AnswerMapper.toEntity(dto, question);
        answerDao.create(answer);
    }

    public Optional<AnswerResponseDto> getAnswer(UUID id) {
        return answerDao.findById(id).map(AnswerMapper::toDto);
    }

    public List<AnswerResponseDto> getAllAnswers() {
        return answerDao.findAll().stream()
                .map(AnswerMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnswerResponseDto> getAnswersByQuestion(UUID questionId) {
        return answerDao.findByQuestionId(questionId).stream()
                .map(AnswerMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean deleteAnswer(UUID id) {
        return answerDao.findById(id).map(answer -> {
            answerDao.delete(answer);
            return true;
        }).orElse(false);
    }

    public boolean updateAnswer(UUID id, AnswerUpdateDto dto) {
        return answerDao.findById(id).map(answer -> {
            AnswerMapper.updateEntity(answer, dto);
            answerDao.update(answer);
            return true;
        }).orElse(false);
    }

    public void createDirect(Answer answer) {
        answerDao.create(answer);
    }

}
