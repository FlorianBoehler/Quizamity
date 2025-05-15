package com.quizamity.service;

import com.quizamity.dao.QuestionDao;
import com.quizamity.model.Question;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class QuestionService {

    @Inject
    private QuestionDao questionDao;

    public void createQuestion(Question question) {
        questionDao.create(question);
    }

    public Optional<Question> getQuestion(UUID id) {
        return questionDao.findById(id);
    }

    public Optional<Question> findByText(String text) {
        return questionDao.findByText(text);
    }

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public boolean updateQuestion(UUID id, Question updatedQuestion) {
        return questionDao.findById(id).map(question -> {
            question.setText(updatedQuestion.getText());
            question.setDifficulty(updatedQuestion.getDifficulty());
            question.setCategory(updatedQuestion.getCategory());
            question.setCreatedBy(updatedQuestion.getCreatedBy());
            question.setApprovedBy(updatedQuestion.getApprovedBy());
            question.setApproved(updatedQuestion.isApproved());
            questionDao.update(question);
            return true;
        }).orElse(false);
    }

    public boolean deleteQuestion(UUID id) {
        return questionDao.findById(id).map(question -> {
            questionDao.delete(question);
            return true;
        }).orElse(false);
    }
}
