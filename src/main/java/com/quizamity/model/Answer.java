package com.quizamity.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

    // Standard-Constructor
    public Answer() {}

    // Convenience-Constructor
    public Answer(Question question, String text, boolean isCorrect) {
        this.question = question;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    // Getter & Setter

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }


    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }
}
