package graphdemo1;

import java.util.*;

/**
 * Represents a node in a graph. A node has a unique identifier and maintains a list of edges (neighbours)
 * that connect it to other nodes.
 */
public class Node {

    private int id; // Unique identifier for the node
    private List<Edge> neighbours = new ArrayList<>(); // List of edges connected to this node

    /**
     * Constructor for the Node class.
     *
     * @param id The unique identifier for the node.
     */
    public Node(int id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of this node.
     *
     * @return The ID of the node.
     */
    public int getNodeID() {
        return this.id;
    }

    /**
     * Adds an edge to the list of neighbours for this node.
     * If the edge already exists, a message is printed instead.
     *
     * @param e The edge to be added as a neighbour.
     */
    public void addNeighbour(Edge e) {
        if (this.neighbours.contains(e)) {
            System.out.println("This edge has already been used for this node.");
        } else {
            System.out.println("Successfully added " + e);
            this.neighbours.add(e);
        }
    }

    /**
     * Prints all the edges (neighbours) connected to this node,
     * including details of each edge and its associated nodes.
     */
    public void getNeighbours() {
        System.out.println("List of all edges that node " + this.id + " has:");
        System.out.println("=================================================");

        for (int i = 0; i < this.neighbours.size(); i++) {
            System.out.println("ID of Edge: " + neighbours.get(i).getID() +
                    "\nID of the first node: " + neighbours.get(i).getIDOfStartNode() +
                    "\nID of the second node: " + neighbours.get(i).getIDOfEndNode());
            System.out.println();
        }
        System.out.println(neighbours);
    }

    /**
     * Returns a string representation of this node, which is its ID.
     *
     * @return A string representation of the node ID.
     */
    @Override
    public String toString() {
        return "" + this.id;
    }
}
