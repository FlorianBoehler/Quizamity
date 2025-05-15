package com.quizamity.dao;

import com.quizamity.model.Question;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class QuestionDao {

    @PersistenceContext(unitName = "quizamityPU")
    private EntityManager em;

    public void create(Question question) {
        em.persist(question);
    }

    public Optional<Question> findById(UUID id) {
        return Optional.ofNullable(em.find(Question.class, id));
    }

    public Optional<Question> findByText(String text) {
        return em.createQuery("SELECT q FROM Question q WHERE q.text = :text", Question.class)
                .setParameter("text", text)
                .getResultStream()
                .findFirst();
    }


    public List<Question> findAll() {
        return em.createQuery("SELECT q FROM Question q", Question.class).getResultList();
    }

    public Question update(Question question) {
        return em.merge(question);
    }

    public void delete(Question question) {
        em.remove(em.contains(question) ? question : em.merge(question));
    }
}
