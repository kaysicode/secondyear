package prelims_laboratory.E;

public class SinglyCircularTester {
    public static void main(String[] args) {
        MySinglyLinkedCircularList<Integer> sll = new MySinglyLinkedCircularList<>();

        // Add elements to the circular linked list
        sll.addNext(19);
        sll.addNext(21);
        sll.addNext(20);
        sll.addNext(1);
        sll.addNext(5);

        try {
            // Display the current linked list
            System.out.println("Current List: ");
            sll.display();
            System.out.println();

            Thread.sleep(3000);

            // Get the size of the linked list
            System.out.println("Size of the list: " + sll.size());
            System.out.println();

            Thread.sleep(3000);

            // Search for an element in the linked list
            System.out.println("Search for element 21: ");
            System.out.println(sll.isSearch(21));  // Search for element 21
            System.out.println();

            Thread.sleep(3000);

            // Delete an element from the linked list
            System.out.println("Delete element 1: ");
            System.out.println(sll.delete(1));  // Delete element 1
            System.out.println();

            Thread.sleep(3000);

            // Display the updated linked list
            System.out.println("Updated List: ");
            sll.display();
            System.out.println();

            Thread.sleep(3000);

            // Insert an element at a specific position
            System.out.println("Inserting 50 at index 2");
            sll.insertAt(2, 50);  // Insert 50 at index 2
            sll.display();
            System.out.println();

            Thread.sleep(3000);

            // Get the first and last elements in the list
            System.out.println("First element: " + sll.getFirst());
            System.out.println("Last element: " + sll.getLast());
            System.out.println();

            Thread.sleep(3000);

            // Clear the list
            System.out.println("Clearing the list...");
            sll.clear();
            System.out.println("Size after clearing: " + sll.size());
            sll.display();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
