package prelims_laboratory.F;
public class DoublyCircularTester {
    public static void main(String[] args) {
        // Create an instance of DoublyLinkedCircularList with Integer data type
        MyDoublyLinkedCircularList<Integer> dll = new MyDoublyLinkedCircularList<>();

        // Add elements to the circular doubly linked list
        dll.addNext(19);
        dll.addNext(21);
        dll.addNext(20);
        dll.addNext(1);
        dll.addNext(5);

        try {

            // Display the current linked list
            System.out.println("Current List: ");
            dll.display();
            System.out.println();

            Thread.sleep(2000);

            // Display the linked list in reverse order
            System.out.println("List in Reverse: ");
            dll.displayReverse();
            System.out.println();

            Thread.sleep(2000);

            // Get the size of the linked list
            System.out.println("Size of the list: " + dll.size());
            System.out.println();

            Thread.sleep(2000);

            // Search for an element in the linked list
            System.out.println("Search for element 21: ");
            System.out.println(dll.isSearch(21));  // Search for element 21
            System.out.println();

            Thread.sleep(2000);

            // Delete an element from the linked list
            System.out.println("Delete element 1: ");
            System.out.println(dll.delete(1));  // Delete element 1
            System.out.println();

            Thread.sleep(2000);

            // Display the updated linked list
            System.out.println("Updated List: ");
            dll.display();
            System.out.println();

            Thread.sleep(2000);

            // Insert an element at a specific position
            System.out.println("Inserting 50 at index 2");
            dll.insertAt(2, 50);  // Insert 50 at index 2
            dll.display();
            System.out.println();

            Thread.sleep(2000);

            // Get the first and last elements in the list
            System.out.println("First element: " + dll.getFirst());
            System.out.println("Last element: " + dll.getLast());
            System.out.println();

            Thread.sleep(2000);

            // Clear the list
            System.out.println("Clearing the list...");
            dll.clear();
            System.out.println("Size after clearing: " + dll.size());
            dll.display();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
