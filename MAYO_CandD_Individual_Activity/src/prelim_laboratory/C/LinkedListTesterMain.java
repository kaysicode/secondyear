package prelim_laboratory.C;

public class LinkedListTesterMain {
    public static void main(String[] args) {
        // Create an instance of SinglyLinkedList with Integer data type
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();

        // Add elements to the linked list
        sll.addNext(19);
        sll.addNext(21);
        sll.addNext(20);
        sll.addNext(1);
        sll.addNext(5);

        // Display the current linked list
        sll.display();
        System.out.println();

        // Search for an element in the linked list
        System.out.println("Search (21) : ");
        System.out.println(sll.isSearch(21));  // Search for element 21
        System.out.println();

        // Delete an element from the linked list
        System.out.println("Delete (1) : ");
        System.out.println(sll.delete(1));  // Delete element 1
        System.out.println();

        // Display the updated linked list
        sll.display();
    }
}
