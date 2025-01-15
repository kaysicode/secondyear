package prelim_laboratory.D;
/**
 * A generic node class for a doubly linked list.
 *
 * @param <T> the type of the data stored in the node
 */
public class Node<T> {
    private T data;           // The data stored in the node
    private Node<T> next;     // Reference to the next node
    private Node<T> prev;     // Reference to the previous node

    /**
     * Constructor to initialize a node with data.
     * Both next and prev references are set to null initially.
     *
     * @param data the data to be stored in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
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

    /**
     * Sets the reference to the previous node.
     *
     * @param prev the previous node in the linked list
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    /**
     * Gets the reference to the previous node.
     *
     * @return the previous node in the linked list
     */
    public Node<T> getPrev() {
        return prev;
    }
}
