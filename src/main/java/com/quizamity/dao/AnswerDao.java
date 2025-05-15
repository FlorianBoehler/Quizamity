package com.quizamity.dao;

import com.quizamity.model.Answer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class AnswerDao {

    @PersistenceContext(unitName = "quizamityPU")
    private EntityManager em;

    public void create(Answer answer) {
        em.persist(answer);
    }

    public Optional<Answer> findById(UUID id) {
        return Optional.ofNullable(em.find(Answer.class, id));
    }

    public List<Answer> findAll() {
        return em.createQuery("SELECT a FROM Answer a", Answer.class).getResultList();
    }

    public List<Answer> findByQuestionId(UUID questionId) {
        return em.createQuery("SELECT a FROM Answer a WHERE a.question.id = :questionId", Answer.class)
                .setParameter("questionId", questionId)
                .getResultList();
    }

    public Answer update(Answer answer) {
        return em.merge(answer);
    }

    public void delete(Answer answer) {
        em.remove(em.contains(answer) ? answer : em.merge(answer));
    }
}
