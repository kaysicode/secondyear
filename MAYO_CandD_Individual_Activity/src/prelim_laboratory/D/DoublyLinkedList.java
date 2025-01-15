package prelim_laboratory.D;
/**
 *<span style="color:white;">Name:MAYO,Kenneth Charles P.</span><br>
 *<span style="color:white;">Code:IT-212L9458B</span><br>
 *<span style="color:white;">Date:September-10-2024</span><br>
 * A doubly linked list implementation.
 *
 * @param <T> the type of elements in the linked list
 */
public class DoublyLinkedList<T> {
    private Node<T> head;    // Reference to the head node
    private Node<T> tail;    // Reference to the tail node

    /**
     * Constructor to initialize an empty doubly linked list.
     * Both head and tail are set to null.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds a new node with the given data at the end of the list.
     *
     * @param data the data to be added to the list
     */
    public void addNext(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.tail == null) {
            // If the list is empty, both head and tail point to the new node
            this.head = newNode;
            this.tail = head;
        } else {
            // Add new node to the end of the list
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            this.tail = newNode;
        }
    }

    /**
     * Displays the list from head to tail.
     */
    public void display() {
        if (this.head == null) {
            System.out.println("List is empty");
            return;
        }

        Node<T> current = this.head;
        while (current != null) {
            System.out.print(current.getData() + " <-> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    /**
     * Deletes the node with the given data from the list.
     *
     * @param data the data to be deleted from the list
     */
    public boolean delete(T data) {
        if (this.head == null) {
            System.out.println("List is empty");
            return false;
        }

        // Check if the head contains the data
        if (this.head.getData().equals(data)) {
            this.head = this.head.getNext();
            if (this.head != null) {
                this.head.setPrev(null);
            } else {
                this.tail = null;
            }
            return true;
        }

        Node<T> current = this.head;

        // Traverse to find the node to delete
        while (current != null && !current.getData().equals(data)) {
            current = current.getNext();
        }

        if (current == null) {
            System.out.println("Element not found");
            return false;
        }

        // Update links to remove the node
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        }
        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext());
        }

        if (current == this.tail) {
            this.tail = current.getPrev();
        }
        return true;
    }

    /**
     * Searches for a node with the given data.
     *
     * @param data the data to search for
     * @return true if the data is found, false otherwise
     */
    public boolean search(T data) {
        Node<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Displays the list from tail to head for reverse traversal.
     */
    public void displayReverse() {
        if (this.tail == null) {
            System.out.println("List is empty");
            return;
        }

        Node<T> current = this.tail;
        while (current != null) {
            System.out.print(current.getData() + " <-> ");
            current = current.getPrev();
        }
        System.out.println("null");
    }
}
