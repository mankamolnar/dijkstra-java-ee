package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.List;
import java.util.Random;

/**
 * Created by flowerpower on 2017. 06. 17..
 */
public class DijkstraFinder {

    // *** PROPERTIES ***
    private List<UserNode> users;
    private UserNode base;
    private UserNode destination;
    private DijkstraSearchTable searchTable;

    // *** PUBLIC METHODS ***
    public DijkstraFinder(List<UserNode> users, int maxDistance) {
        this.users = users;
        this.searchTable = new DijkstraSearchTable(maxDistance);
    }

    public void setUpSearch(UserNode base, UserNode destination) {
        this.base = base;
        this.destination = destination;
    }

    public void setUpRandomSearch() {
        UserNode re1 = randomElement();
        UserNode re2 = randomElement();

        System.out.println("Base: " + re1.getFirstName() + " " + re1.getLastName());
        System.out.println("Destination: " + re2.getFirstName() + " " + re2.getLastName());

        setUpSearch(re1, re2);
    }

    public void shortestPath(UserNode userNode, DijkstraPath path) {
        if (path.size() >= searchTable.getMaxDistance()) {
            return;
        }

        DijkstraPath newPath = new DijkstraPath();
        newPath.setPath(path);
        newPath.add(userNode);
        searchTable.add(userNode, newPath.size(), newPath.getPath());

        for (UserNode friend : userNode.getFriends()) {
            if (!path.contains(friend)) {
                shortestPath(friend, newPath);

            }
        }

    }

    // *** PRIVATE METHODS ***
    private UserNode randomElement() {
        int idx = new Random().nextInt(users.size());
        return users.get(idx);
    }

    // *** GETTERS AND SETTERS ***
    public UserNode getBase() {
        return base;
    }

    public UserNode getDestination() {
        return destination;
    }

    public DijkstraSearchTable getSearchTable() {
        return searchTable;
    }


}
