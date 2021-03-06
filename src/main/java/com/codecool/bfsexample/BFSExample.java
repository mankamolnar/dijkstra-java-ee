package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class BFSExample {

    public static final int MAX_FRIENDSHIP_DEPTH = 6;

    public static List<UserNode> populateDB(EntityManager em) {

        RandomDataGenerator generator = new RandomDataGenerator(MAX_FRIENDSHIP_DEPTH);
        List<UserNode> users = generator.generate();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (UserNode user : users) {
            em.persist(user);
        }
        transaction.commit();

        return users;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bfsExampleUnit");
        EntityManager em = emf.createEntityManager();
        em.clear();

        List<UserNode> users = populateDB(em);
        GraphPlotter.plot(users);

        DijkstraFinder dijkstra = new DijkstraFinder(users, MAX_FRIENDSHIP_DEPTH);

        dijkstra.setUpRandomSearch();
        GraphPlotter.paintUserToRed(dijkstra.getBase());
        GraphPlotter.paintUserToRed(dijkstra.getDestination());

        dijkstra.shortestPath(dijkstra.getBase(), new DijkstraPath(), 0);
        System.out.println("Distance: " + dijkstra.getSearchTable().findAndGet(dijkstra.getDestination()).getDistance());
        System.out.println("Shortest path: " + dijkstra.getSearchTable().findAndGet(dijkstra.getDestination()).getPath());
        GraphPlotter.paintRoute(dijkstra.getSearchTable(), dijkstra.getDestination());

        dijkstra.friendsInRange(1);
        System.out.println("Friends in 1 range: " + dijkstra.getSearchTable());


    }
}
