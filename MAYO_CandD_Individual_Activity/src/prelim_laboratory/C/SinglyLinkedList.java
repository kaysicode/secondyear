package prelim_laboratory.C;
/**
 *<span style="color:white;">Name:MAYO,Kenneth Charles P.</span><br>
 *<span style="color:white;">Code:IT-212L9458B</span><br>
 *<span style="color:white;">Date:September-10-2024</span><br>
 * A generic singly linked list implementation that stores elements of type T.
 * @param <T> the type of elements stored in this linked list
 */
public class SinglyLinkedList<T> {
    private Node<T> head; // Head node of the list
    private Node<T> tail; // Tail node of the list

    /**
     * Constructs an empty singly linked list.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds a new element to the end of the linked list.
     *
     * @param data the data to be added to the list
     */
    public void addNext(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.tail == null) {
            // If the list is empty, set both head and tail to the new node
            this.head = newNode;
            this.tail = head;
        } else {
            // Add the new node to the end of the list
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
    }

    /**
     * Displays all the elements in the linked list from head to tail.
     * If the list is empty, it prints "List is empty".
     */
    public void display() {
        if (this.head == null) {
            System.out.println("List is empty");
            return;
        }

        Node<T> current = this.head;
        while (current != null) {
            System.out.print(current.getData() + ", ");
            current = current.getNext();
        }
        System.out.println();
    }

    /**
     * Deletes the first occurrence of the specified element from the linked list.
     *
     * @param data the element to be deleted from the list
     * @return true if the element was found and deleted, false otherwise
     */
    public boolean delete(T data) {
        if (this.head == null) {
            System.out.println("List is empty");
            return false;
        }

        // If the element to delete is the head
        if (this.head.getData().equals(data)) {
            this.head = this.head.getNext();
            if (this.head == null) {
                this.tail = null; // List is now empty
            }
            return true;
        }

        Node<T> current = this.head;
        Node<T> previous = null;

        // Traverse the list to find the element to delete
        while (current != null && !current.getData().equals(data)) {
            previous = current;
            current = current.getNext();
        }

        // Element not found
        if (current == null) {
            return false;
        }

        // Remove the node from the list
        previous.setNext(current.getNext());

        // If the node to delete is the tail, update the tail
        if (current == this.tail) {
            this.tail = previous;
        }

        return true;
    }

    /**
     * Searches for an element in the linked list.
     *
     * @param data the element to search for in the list
     * @return true if the element is found, false if not
     */
    public boolean isSearch(T data) {
        Node<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
