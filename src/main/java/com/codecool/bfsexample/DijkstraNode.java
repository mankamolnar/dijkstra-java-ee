package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import java.util.List;

/**
 * Created by flowerpower on 2017. 06. 17..
 */
public class DijkstraNode {

    // *** PROPERTIES ***
    private UserNode userNode;
    private int distance;
    private List<UserNode> path;

    // *** PUBLIC METHODS ***
    public DijkstraNode(UserNode userNode, int distance, List<UserNode> path) {
        this.userNode = userNode;
        this.distance = distance;
        this.path = path;
    }

    public void buildPath(UserNode userNode) {
        path.add(userNode);
    }

    // *** GETTERS AND SETTERS ***
    public List<UserNode> getPath() {
        return path;
    }

    public UserNode getUserNode() {
        return userNode;
    }

    public int getDistance() {
        return distance;
    }

    public void setPath(List<UserNode> path) {
        this.path = path;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return userNode.getFirstName() + " " + distance + " " + path;
    }
}
