package graphdemo1;

/**
 * Represents an edge in a graph. An edge connects two nodes (start and end)
 * and can have an associated weight and a unique identifier.
 */
public class Edge {
    private Node start; // Starting node of the edge
    private Node end; // Ending node of the edge
    private double weight; // Weight of the edge
    private int id; // Unique identifier for the edge

    /**
     * Constructor for the Edge class.
     *
     * @param s  The starting node of the edge.
     * @param e  The ending node of the edge.
     * @param w  The weight of the edge.
     * @param id The unique identifier of the edge.
     */
    public Edge(Node s, Node e, double w, int id) {
        this.start = s;
        this.end = e;
        this.weight = w;
        this.id = id;
    }

    /**
     * Gets the unique identifier of this edge.
     *
     * @return The ID of the edge.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Gets the starting node of this edge.
     *
     * @return The start node of the edge.
     */
    public Node getStart() {
        return this.start;
    }

    /**
     * Gets the ID of the starting node of this edge.
     *
     * @return The ID of the start node.
     */
    public int getIDOfStartNode() {
        return this.start.getNodeID();
    }

    /**
     * Gets the ending node of this edge.
     *
     * @return The end node of the edge.
     */
    public Node getEnd() {
        return this.end;
    }

    /**
     * Gets the ID of the ending node of this edge.
     *
     * @return The ID of the end node.
     */
    public int getIDOfEndNode() {
        return this.end.getNodeID();
    }

    /**
     * Gets the weight of this edge.
     *
     * @return The weight of the edge.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns a string representation of this edge in the form (start, end).
     *
     * @return A string representation of the edge.
     */
    @Override
    public String toString() {
        return "(" + start + "," + end + ")";
    }
}
