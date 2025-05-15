package com.quizamity.service;

import com.quizamity.dao.AnswerDao;
import com.quizamity.model.Answer;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class AnswerService {

    @Inject
    private AnswerDao answerDao;

    public void createAnswer(Answer answer) {
        answerDao.create(answer);
    }

    public Optional<Answer> getAnswer(UUID id) {
        return answerDao.findById(id);
    }

    public List<Answer> getAllAnswers() {
        return answerDao.findAll();
    }

    public List<Answer> getAnswersByQuestion(UUID questionId) {
        return answerDao.findByQuestionId(questionId);
    }

    public boolean updateAnswer(UUID id, Answer updated) {
        return answerDao.findById(id).map(answer -> {
            answer.setText(updated.getText());
            answer.setCorrect(updated.isCorrect());
            answer.setQuestion(updated.getQuestion());
            answerDao.update(answer);
            return true;
        }).orElse(false);
    }

    public boolean deleteAnswer(UUID id) {
        return answerDao.findById(id).map(answer -> {
            answerDao.delete(answer);
            return true;
        }).orElse(false);
    }
}
