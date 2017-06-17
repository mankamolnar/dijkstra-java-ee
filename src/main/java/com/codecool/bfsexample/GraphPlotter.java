package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.ArrayList;
import java.util.List;


public class GraphPlotter {

    public static Graph graph;
    public static List<Node> nodes = new ArrayList<>();
    public static List<Node> edges = new ArrayList<>();

    public static void plot(List<UserNode> points) {
        graph = new SingleGraph("Friend circle");

        for (UserNode userNode : points) {

            String insertGraphId = Long.toString(userNode.getId());
            nodes.add(graph.addNode(insertGraphId));

            int insertedArrayIndex = nodes.size()-1;
            nodes.get(insertedArrayIndex).addAttribute("ui.label", userNode.getFirstName() + " " + userNode.getLastName());

        }

        for (UserNode userNode : points) {
            for (UserNode n1 : userNode.getFriends()) {
                String leftKey = Long.toString(userNode.getId());
                String rightKey = Long.toString(n1.getId());
                String edgeId = leftKey + "-" + rightKey;

                if (graph.getEdge(edgeId) == null && graph.getEdge(rightKey + "-" + leftKey) == null) {
                    edges.add(graph.addEdge(leftKey + "-" + rightKey, leftKey, rightKey));
                }
            }
        }

        graph.display();

    }

    public static void paintUserToRed(UserNode user) {
        for (Node node : nodes) {
            if (node.getId().equals(Long.toString(user.getId()))) {
                node.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
            }
        }
    }

    public static void paintEdgeToRed(UserNode from, UserNode to) {
        Edge edge = graph.getEdge(Long.toString(to.getId())+"-"+Long.toString(from.getId()));
        if (edge == null) {
            edge = graph.getEdge(Long.toString(from.getId())+"-"+Long.toString(to.getId()));
        }
        edge.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
    }

    public static void paintRoute(DijkstraSearchTable table, UserNode destionation) {
        //FIND PATH TO USER
        for (DijkstraNode userTo : table.getTable()) {
            if (userTo.getUserNode().getId() == destionation.getId()) {

                //PAINT USER NODES
                for (int i = 0; i < userTo.getPath().size()-1; i++) {
                    UserNode currentNode = userTo.getPath().get(i);
                    UserNode nextNode = userTo.getPath().get(i+1);
                    paintUserToRed(currentNode);
                    paintEdgeToRed(currentNode, nextNode);
                }

            }
        }
    }
}
