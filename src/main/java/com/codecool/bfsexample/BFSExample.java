package com.codecool.bfsexample;

import com.codecool.bfsexample.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BFSExample {

    public static void populateDB(EntityManager em) {

        User user1 = new User("John", "Smith");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user1);
        transaction.commit();
        System.out.println("saved.");
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bfsExampleUnit");
        EntityManager em = emf.createEntityManager();

        em.clear();
        populateDB(em);

    }
}
