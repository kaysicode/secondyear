package prelim_laboratory.D;

public class DoublyLinkedTesterMain {
    public static void main(String[] args) {
        // Create an instance of DoublyLinkedList with Integer data type
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        // Add elements to the doubly linked list
        dll.addNext(19);
        dll.addNext(21);
        dll.addNext(20);
        dll.addNext(1);
        dll.addNext(5);

        // Display the current doubly linked list from head to tail
        System.out.println("Doubly Linked List (head to tail):");
        dll.display();
        System.out.println();

        // Display the doubly linked list in reverse order (tail to head)
        System.out.println("Doubly Linked List (tail to head):");
        dll.displayReverse();
        System.out.println();

        // Search for an element in the doubly linked list
        System.out.println("Search : (21)");
        System.out.println(dll.search(21));  // Search for element 21
        System.out.println();

        // Delete an element from the doubly linked list
        System.out.println("Delete : (1)");
        System.out.println(dll.delete(1));  // Delete element 1
        System.out.println();

        // Display the updated doubly linked list from head to tail
        System.out.println("Updated Doubly Linked List (head to tail):");
        dll.display();
        System.out.println();

        // Display the updated doubly linked list in reverse order (tail to head)
        System.out.println("Updated Doubly Linked List (tail to head):");
        dll.displayReverse();
    }
}
