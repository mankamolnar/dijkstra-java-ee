package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flowerpower on 2017. 06. 17..
 */
public class DijkstraSearchTable {
    private List<DijkstraNode> table;
    private int maxDistance;

    public DijkstraSearchTable(int maxDistance) {
        this.table = new ArrayList<>();
        this.maxDistance = maxDistance * maxDistance;

    }

    public void add(UserNode userNode, int distance, List<UserNode> path) {
        int nodeIndex = find(userNode);

        if (nodeIndex == 0) {
            table.add(new DijkstraNode(userNode, distance, path));

        } else if (nodeIndex > 0) {
            if (table.get(nodeIndex).getDistance() > distance) {
                table.get(nodeIndex).setPath(path);
                table.get(nodeIndex).setDistance(distance);

            }
        }
    }

    public int find(UserNode userNode) {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getUserNode().getId() == userNode.getId()) {
                return i;
            }
        }
        return 0;
    }

    public DijkstraNode findAndGet(UserNode userNode) {
        for (int i = 0; i < table.size(); i++) {
            DijkstraNode currentNode = table.get(i);
            if (currentNode.getUserNode().getId() == userNode.getId()) {
                return currentNode;
            }
        }
        return null;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public List<DijkstraNode> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
