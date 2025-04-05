package com.quizamity.test;

import com.quizamity.model.User;

import jakarta.persistence.*;

public class UserTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizamityPU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        User user = new User(
                "max125",
                "$2a$12$abc123hashedpw", // dummy
                "max125@example.com",
                "STUDENT"
        );

        em.persist(user);

        tx.commit();

        System.out.println("User saved with ID: " + user.getId());

        em.close();
        emf.close();
    }
}
