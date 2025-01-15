package prelims_laboratory.F;
/**
 *<span style="color:white;">Name:MAYO,Kenneth Charles P.</span><br>
 *<span style="color:white;">Code:IT-212L9458B</span><br>
 *<span style="color:white;">Date:September-18-2024</span><br>
 * A doubly linked circular list  implementation.
 *
 * @param <T> the type of elements in the linked list
 */
public class MyDoublyLinkedCircularList<T> {
    private Node<T> head;  // Head node of the list
    private Node<T> tail;  // Tail node of the list
    private int size;      // Size of the list

    /**
     * Constructs an empty doubly linked circular list.
     */
    public MyDoublyLinkedCircularList() {
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
            // If the list is empty, set both head and tail to the new node, and point them to each other
            this.head = newNode;
            this.tail = newNode;
            this.head.setNext(head);
            this.head.setPrev(tail);
        } else {
            // Add the new node to the end of the list and maintain circular links
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            newNode.setNext(this.head);
            this.tail = newNode;
            this.head.setPrev(this.tail);
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
     * Displays all the elements in reverse order, from tail to head.
     * If the list is empty, it prints "List is empty".
     */
    public void displayReverse() {
        if (this.head == null) {
            System.out.println("List is empty");
            return;
        }

        Node<T> current = this.tail;
        do {
            System.out.print(current.getData() + ", ");
            current = current.getPrev();
        } while (current != this.tail);
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

        // Special case: Deleting the head node
        if (this.head.getData().equals(data)) {
            if (this.head == this.tail) {
                // Only one node in the list
                this.head = null;
                this.tail = null;
            } else {
                this.head = this.head.getNext();
                this.head.setPrev(this.tail);
                this.tail.setNext(this.head);
            }
            size--; // Decrement size of the list
            return true;
        }

        // Traverse the list to find the element to delete
        do {
            current = current.getNext();
        } while (current != this.head && !current.getData().equals(data));

        // If the element was not found
        if (current == this.head) {
            return false;
        }

        // Remove the node
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());

        // If the node to delete is the tail, update the tail
        if (current == this.tail) {
            this.tail = current.getPrev();
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
                tail.setNext(head);
                head.setPrev(tail); // Circular link
            } else {
                newNode.setNext(head);
                newNode.setPrev(tail);
                head.setPrev(tail);
                tail.setNext(newNode);
                head = newNode;
            }
        } else if (index == size) {
            // Insert at tail
            addNext(data);
            return;
        } else {
            // Insert in the middle
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            newNode.setPrev(current);
            current.getNext().setPrev(newNode);
            current.setNext(newNode);
        }
        size++;
    }
}
