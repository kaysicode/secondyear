package Activities;

public class Main {
    public static void main(String[] args) {
        // Create a new LinkedList of Integer type
        LinkedList<Integer> list = new LinkedList<>();

        // Test if the list is empty
        System.out.println("Is the list empty? " + list.isEmpty());  // Expected: true

        // Add elements to the front and rear of the list
        list.addAtTheFront(10);
        list.addAtTheFront(20);
        list.addAtTheRear(30);
        list.addAtTheRear(40);

        // Display the list after adding elements
        System.out.println("List after adding elements: " + list);  // Expected: [20, 10, 30, 40]

        // Test if the list is still empty
        System.out.println("Is the list empty? " + list.isEmpty());  // Expected: false

        // Test getting the front and rear nodes
        System.out.println("Front element: " + list.getFront().getData());  // Expected: 20
        System.out.println("Rear element: " + list.getRear().getData());    // Expected: 40

        // Test deleting elements from the front and rear
        System.out.println("Deleted from front: " + list.deleteFromTheFront());  // Expected: 20
        System.out.println("Deleted from rear: " + list.deleteFromTheRear());    // Expected: 40

        // Display the list after deletions
        System.out.println("List after deleting elements: " + list);  // Expected: [10, 30]

        // Add more elements and display the list
        list.addAtTheFront(50);
        list.addAtTheRear(60);
        System.out.println("List after more additions: " + list);  // Expected: [50, 10, 30, 60]

        // Test delete from empty list
        list.deleteFromTheFront();
        list.deleteFromTheFront();
        list.deleteFromTheFront();
        list.deleteFromTheFront();
        System.out.println("List after deleting all elements: " + list);  // Expected: []

        // Test empty state after deletions
        System.out.println("Is the list empty now? " + list.isEmpty());  // Expected: true
    }


}
