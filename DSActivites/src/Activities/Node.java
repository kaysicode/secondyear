package Activities;

import java.util.Objects;

public class Node <T> {
    private T data;
    private Node<T> nextNode;

    /**
     * Default constructor initializing the node with null values for data and nextNode.
     */
    public Node() {
        this.data = null;
        this.nextNode = null;
    }

    /**
     * Parameterized constructor allowing initialization of node with specific data and next node.
     *
     * @param data the data to be stored in the node
     * @param nextNode the reference to the next node in the list
     */
    public Node(T data, Node<T> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data of this node
     */
    public T getData() { return data; }

    /**
     * Sets the data for this node.
     *
     * @param data the new data to be stored in this node
     */
    public void setData(T data) {this.data = data;}

    /**
     * Returns the reference to the next node in the list.
     *
     * @return the next node
     */
    public Node<T> getNextNode() {return nextNode;}

    /**
     * Sets the reference to the next node in the list.
     *
     * @param nextNode the new reference to the next node
     */
    public void setNextNode(Node<T> nextNode) {this.nextNode = nextNode;}

    /**
     * Compares this node to another node for equality.
     * Nodes are considered equal if both their data and nextNode fields are equal.
     *
     * @param otherNodes the node to compare with
     * @return true if the nodes are equal, false otherwise
     */
    public boolean equals(Node<T> otherNodes) {
        if (this == otherNodes) return true;
        if (otherNodes == null || getClass() != otherNodes.getClass()) return false;
        Node<?> node = otherNodes;
        return Objects.equals(data, node.data) && Objects.equals(nextNode, node.nextNode);
    }
}
