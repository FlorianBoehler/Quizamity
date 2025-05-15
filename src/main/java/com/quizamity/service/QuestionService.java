package com.quizamity.service;

import com.quizamity.dao.QuestionDao;
import com.quizamity.dto.QuestionCreateDto;
import com.quizamity.dto.QuestionResponseDto;
import com.quizamity.dto.QuestionUpdateDto;
import com.quizamity.mapper.QuestionMapper;
import com.quizamity.model.Category;
import com.quizamity.model.Question;
import com.quizamity.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class QuestionService {

    @Inject
    private QuestionDao questionDao;

    @Inject
    private CategoryService categoryService;

    @Inject
    private UserService userService;

    public void createQuestion(QuestionCreateDto dto) {
        Category category = categoryService.getCategory(dto.categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Kategorie nicht gefunden"));
        User createdBy = userService.getUserEntity(dto.createdByUserId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));

        Question question = QuestionMapper.toEntity(dto, category, createdBy);
        questionDao.create(question);
    }

    public boolean updateQuestion(UUID id, QuestionUpdateDto dto) {
        return questionDao.findById(id).map(existing -> {
            Category category = categoryService.getCategory(dto.categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Kategorie nicht gefunden"));

            QuestionMapper.updateEntity(existing, dto, category);
            questionDao.update(existing);
            return true;
        }).orElse(false);
    }

    public Optional<QuestionResponseDto> getQuestion(UUID id) {
        return questionDao.findById(id).map(QuestionMapper::toDto);
    }

    public List<QuestionResponseDto> getAllQuestions() {
        return questionDao.findAll().stream()
                .map(QuestionMapper::toDto)
                .toList();
    }

    public boolean deleteQuestion(UUID id) {
        return questionDao.findById(id).map(question -> {
            questionDao.delete(question);
            return true;
        }).orElse(false);
    }


    public Optional<Question> findByText(String text) {
        return questionDao.findByText(text);
    }

    public void createDirect(Question question) {
        questionDao.create(question);
    }
}
