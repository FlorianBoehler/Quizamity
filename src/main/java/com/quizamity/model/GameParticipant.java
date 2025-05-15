package com.quizamity.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Entity
@Table(name = "game_participants")
public class GameParticipant {

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

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    // Standard-Constructor
    public GameParticipant() {}

    // Convenience-Constructor
    public GameParticipant(Game game, User user, int score, LocalDateTime joinedAt) {
        this.game = game;
        this.user = user;
        this.score = score;
        this.joinedAt = joinedAt;
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

    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}
