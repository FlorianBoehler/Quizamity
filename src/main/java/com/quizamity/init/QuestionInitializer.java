package com.quizamity.init;

import com.quizamity.model.Category;
import com.quizamity.model.Question;
import com.quizamity.model.User;
import com.quizamity.service.CategoryService;
import com.quizamity.service.QuestionService;
import com.quizamity.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.DependsOn;
import jakarta.inject.Inject;

import java.util.List;

@Startup
@Singleton
@DependsOn({"CategoryInitializer", "UserInitializer"})
public class QuestionInitializer {

    @Inject
    private QuestionService questionService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        User student = userService.findByUsername("student")
                .orElseThrow(() -> new IllegalStateException("Student user not found"));
        User moderator = userService.findByUsername("moderator")
                .orElseThrow(() -> new IllegalStateException("Moderator user not found"));
        Category category = categoryService.findByName("IT-Projektmanagement")
                .orElseThrow(() -> new IllegalStateException("Category 'IT-Projektmanagement' not found"));

        List<Question> questions = List.of(
                new Question("Was ist ein Gantt-Diagramm?", 3, category, student, moderator, true),
                new Question("Welche Phasen umfasst der klassische Projektlebenszyklus?", 2, category, student, moderator, true),
                new Question("Was beschreibt der kritische Pfad in einem Projektplan?", 4, category, student, moderator, true),
                new Question("Was ist ein Lastenheft?", 2, category, student, moderator, true),
                new Question("Welche Methode wird bei agilen Projekten zur Sprintplanung verwendet?", 3, category, student, moderator, true),
                new Question("Was ist der Unterschied zwischen Meilenstein und Liefergegenstand?", 3, category, student, moderator, true),
                new Question("Welche Rolle hat ein Product Owner in Scrum?", 3, category, student, moderator, true),
                new Question("Was ist ein Projektstrukturplan (PSP)?", 2, category, student, moderator, true),
                new Question("Wie hilft ein Burndown-Chart beim Projektcontrolling?", 4, category, student, moderator, true),
                new Question("Was bedeutet das magische Dreieck des Projektmanagements?", 3, category, student, moderator, true)
        );

        for (Question q : questions) {
            questionService.findByText(q.getText()).orElseGet(() -> {
                questionService.createDirect(q);
                return q;
            });
        }
    }
}
