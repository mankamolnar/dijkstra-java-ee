package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flowerpower on 2017. 06. 17..
 */
public class DijkstraPath {
    List<UserNode> path;

    public DijkstraPath() {
        this.path = new ArrayList<>();
    }

    public void add(UserNode node) {
        path.add(node);
    }

    public boolean contains(UserNode node) {
        for (UserNode pathNode : path) {
            if (node.getId() == pathNode.getId()) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return path.size();
    }

    public List<UserNode> getPath() {
        return this.path;
    }

    public void setPath(DijkstraPath path) {
        this.path = new ArrayList<>(path.getPath());
    }
}
