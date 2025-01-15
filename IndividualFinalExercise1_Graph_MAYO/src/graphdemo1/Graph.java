package graphdemo1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a graph consisting of nodes.
 * Provides functionality to add nodes and check the graph's status.
 */
public class Graph {

    private List<Node> nodes = new ArrayList<>(); // List of nodes in the graph
    private int numberOfNode = 0; // Counter for the number of nodes in the graph

    /**
     * Checks if the graph contains more than one node.
     *
     * @return {@code true} if the graph has more than one node, {@code false} otherwise.
     */
    public boolean checkForAvailability() {
        return this.numberOfNode > 1;
    }

    /**
     * Adds a new node to the graph and increments the node count.
     *
     * @param node The node to be added to the graph.
     */
    public void createNode(Node node) {
        this.nodes.add(node);
        this.numberOfNode++; // Increment the count of nodes
    }

    /**
     * Gets the current number of nodes in the graph.
     *
     * @return The number of nodes in the graph.
     */
    public int getNumberOfNode() {
        return this.numberOfNode;
    }
}
