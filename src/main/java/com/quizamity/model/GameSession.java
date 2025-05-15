package com.quizamity.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Entity
@Table(name = "game_sessions")
public class GameSession {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int score;

    @Column(name = "correct_answers", nullable = false)
    private int correctAnswers;

    @Column(nullable = false)
    private int mistakes;

    // Standard-Constructor
    public GameSession() {}

    // Convenience-Constructor
    public GameSession(Game game, User user, int score, int correctAnswers, int mistakes) {
        this.game = game;
        this.user = user;
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.mistakes = mistakes;
    }

    // Getter & Setter
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(int correctAnswers) { this.correctAnswers = correctAnswers; }

    public int getMistakes() { return mistakes; }
    public void setMistakes(int mistakes) { this.mistakes = mistakes; }
}
