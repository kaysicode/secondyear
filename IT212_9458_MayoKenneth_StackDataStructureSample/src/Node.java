/**
 * A generic node class used to represent elements in a linked structure.
 * Each node contains a datum and a reference to the next node in the sequence.
 *
 * @param <T> the type of the datum held in this node
 */
public class Node<T> {
    /**
     * The data stored in this node.
     */
    private T datum;

    /**
     * The reference to the next node in the sequence.
     */
    private Node<T> next;

    /**
     * Constructs an empty node with no datum and no reference to the next node.
     */
    public Node() {
        this.datum = null;
        this.next = null;
    }

    /**
     * Constructs a node with the specified datum and next node reference.
     *
     * @param datum the datum to store in this node
     * @param next  the reference to the next node
     */
    public Node(T datum, Node<T> next) {
        this.datum = datum;
        this.next = next;
    }

    /**
     * Returns the datum stored in this node.
     *
     * @return the datum in this node
     */
    public T getDatum() {
        return datum;
    }

    /**
     * Sets the datum of this node.
     *
     * @param datum the datum to set in this node
     */
    public void setDatum(T datum) {
        this.datum = datum;
    }

    /**
     * Returns the reference to the next node in the sequence.
     *
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node in the sequence.
     *
     * @param next the next node to reference
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
} // End of Node Class
