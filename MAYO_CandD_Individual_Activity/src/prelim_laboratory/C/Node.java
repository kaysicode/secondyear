package prelim_laboratory.C;

/**
 * A generic node class for a singly linked list.
 *
 * @param <T> the type of the data stored in the node
 */
public class Node<T> {
    private T data;          // The data stored in the node
    private Node<T> next;    // Reference to the next node

    /**
     * Constructor to initialize a node with data.
     * The next reference is set to null initially.
     *
     * @param data the data to be stored in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Gets the data stored in this node.
     *
     * @return the data of the node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param next the next node in the linked list
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Gets the reference to the next node.
     *
     * @return the next node in the linked list
     */
    public Node<T> getNext() {
        return next;
    }
}
