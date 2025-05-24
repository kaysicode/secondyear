package Activities;

public class LinkedList<T> {

    private Node<T> front;
    private Node<T> rear;

    public LinkedList() {
        this.front = null;
        this.rear = null;
    }

    /**
     * Constructor to initialize the LinkedList with specified front and rear nodes.
     *
     * @param front the front node of the linked list
     * @param rear  the rear node of the linked list
     */
    public LinkedList(Node<T> front, Node<T> rear) {
        this.front = front;
        this.rear = rear;
    }

    /**
     * Gets the front node of the linked list.
     *
     * @return the front node of the linked list
     */
    public Node<T> getFront() {
        return front;
    }

    /**
     * Sets the front node of the linked list.
     *
     * @param front the node to set as the front of the linked list
     */
    public void setFront(Node<T> front) {
        this.front = front;
    }

    /**
     * Gets the rear node of the linked list.
     *
     * @return the rear node of the linked list
     */
    public Node<T> getRear() {
        return rear;
    }

    /**
     * Sets the rear node of the linked list.
     *
     * @param rear the node to set as the rear of the linked list
     */
    public void setRear(Node<T> rear) {
        this.rear = rear;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, otherwise false.
     */
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    /**
     * Adds a new node with the specified data at the front of the list.
     *
     * @param data the data to be added
     */
    public void addAtTheFront(T data) {
        Node<T> newNode = new Node<>(data, front);
        if (isEmpty()) { rear = newNode; }
        front = newNode;
    }

    /**
     * Adds a new node with the specified data at the rear of the list.
     *
     * @param data the data to be added
     */
    public void addAtTheRear(T data) {
        Node<T> newNode = new Node<>(data, null);
        // Move rear to point to the new node
        if (isEmpty()) {
            front = newNode;  // If empty, both front and rear should point to the new node
        } else {
            rear.setNextNode(newNode); // Update the current rear's nextNode to the new node
        }
        rear = newNode;
    }

    /**
     * Deletes the node from the front of the list and returns its data.
     *
     * @return the data of the deleted node, or null if the list is empty
     */
    public T deleteFromTheFront() {
        if (isEmpty()) {
            return null;
        }
        T data = front.getData();
        front = front.getNextNode();
        if (front == null) {
            rear = null;
        }
        return data;
    }

    /**
     * Deletes the node from the rear of the list and returns its data.
     *
     * @return the data of the deleted node, or null if the list is empty
     */
    public T deleteFromTheRear() {
        if (isEmpty()) {
            return null;
        }
        T data = rear.getData();

        if (front == rear) {  // Only one element in the list
            front = rear = null;
        } else {
            Node<T> current = front;
            while (current.getNextNode() != rear) {
                current = current.getNextNode();
            }
            current.setNextNode(null);
            rear = current;
        }
        return data;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> current = front;
        while (current != null) {
            result.append(current.getData());
            if (current.getNextNode() != null) {
                result.append(", ");
            }
            current = current.getNextNode();
        }
        result.append("]");
        return result.toString();
    }
}
