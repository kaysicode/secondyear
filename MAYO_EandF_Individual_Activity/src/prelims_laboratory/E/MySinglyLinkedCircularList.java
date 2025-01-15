package prelims_laboratory.E;
/**
 *<span style="color:white;">Name:MAYO,Kenneth Charles P.</span><br>
 *<span style="color:white;">Code:IT-212L9458B</span><br>
 *<span style="color:white;">Date:September-18-2024</span><br>
 * A generic singly linked circular list implementation that stores elements of type T.
 * @param <T> the type of elements stored in this linked list
 */

public class MySinglyLinkedCircularList<T> {
    private Node<T> head; // Head node of the list
    private Node<T> tail; // Tail node of the list
    private int size;     // Size of the list

    /**
     * Constructs an empty singly linked circular list.
     */
    public MySinglyLinkedCircularList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    /**
     * Adds a new element to the end of the linked list.
     *
     * @param data the data to be added to the list
     */
    public void addNext(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.tail == null) {
            // If the list is empty, set both head and tail to the new node, and point it to itself
            this.head = newNode;
            this.tail = newNode;
            this.tail.setNext(this.head); // Circular link
        } else {
            // Add the new node to the end of the list, and point tail to the new node
            this.tail.setNext(newNode);
            this.tail = newNode;
            this.tail.setNext(this.head); // Maintain circular link
        }
        size++; // Increment size of the list
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
        do {
            System.out.print(current.getData() + ", ");
            current = current.getNext();
        } while (current != this.head);
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

        Node<T> current = this.head;
        Node<T> previous = this.tail;

        // Special case: Deleting the head node
        if (this.head.getData().equals(data)) {
            if (this.head == this.tail) {
                // Only one node in the list
                this.head = null;
                this.tail = null;
            } else {
                this.head = this.head.getNext();
                this.tail.setNext(this.head); // Maintain circular link
            }
            size--; // Decrement size of the list
            return true;
        }

        // Traverse the list to find the element to delete
        do {
            previous = current;
            current = current.getNext();
        } while (current != this.head && !current.getData().equals(data));

        // If the element was not found
        if (current == this.head) {
            return false;
        }

        // Remove the node
        previous.setNext(current.getNext());

        // If the node to delete is the tail, update the tail
        if (current == this.tail) {
            this.tail = previous;
            this.tail.setNext(this.head); // Maintain circular link
        }
        size--; // Decrement size of the list
        return true;
    }

    /**
     * Searches for an element in the linked list.
     *
     * @param data the element to search for in the list
     * @return true if the element is found, false if not
     */
    public boolean isSearch(T data) {
        if (this.head == null) {
            return false;
        }

        Node<T> current = this.head;
        do {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        } while (current != this.head);

        return false;
    }

    /**
     * Returns the size of the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the first element in the list.
     *
     * @return the data of the head node, or null if the list is empty
     */
    public T getFirst() {
        return (head != null) ? head.getData() : null;
    }

    /**
     * Returns the last element in the list.
     *
     * @return the data of the tail node, or null if the list is empty
     */
    public T getLast() {
        return (tail != null) ? tail.getData() : null;
    }

    /**
     * Clears the list, removing all elements.
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /**
     * Inserts a new element at the specified index in the list.
     *
     * @param index the index at which to insert the new element (0-based)
     * @param data  the data to be inserted into the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void insertAt(int index, T data) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            // Insert at head
            if (size == 0) {
                head = newNode;
                tail = newNode;
                tail.setNext(head); // Circular link
            } else {
                newNode.setNext(head);
                head = newNode;
                tail.setNext(head); // Maintain circular link
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);

            if (index == size) {
                tail = newNode; // Update tail if inserted at the end
            }
        }
        size++; // Increment size of the list
    }
}
