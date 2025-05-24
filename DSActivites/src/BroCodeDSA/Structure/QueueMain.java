package BroCodeDSA.Structure;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMain {
    public static void main(String[] args) {

        // ************************************************************************

        // Queue = FIFO Data structure. First-In First-Out (ex/ A line of people)
        //         A collection designed for holding elements prior to processing
        //         Linear data structure

        //         add      = enqueue, offer()
        //         remove   = dequeue, poll()

        //Queue will not get an exception when you poll the rest of the data
        //But, when you use the element() it will.

        // where are queues useful?

        // 1. Keyboard Buffer (letters should appear on the screen in the order they're pressed)
        // 2. Printer Queue (print jobs should be completed in order)
        // 3. Used in LinkedLists, PriorityQueues, Breadth-first search
        // ************************************************************************

        Queue<String> queue = new LinkedList<>();

        queue.offer("Karen");
        queue.offer("Chad");
        queue.offer("Steve");
        queue.offer("Harold");

        System.out.println(queue.contains("Harold"));
//        System.out.println(queue.size());
//        System.out.println(queue.isEmpty());
//        queue.poll();
//        queue.poll();
//        queue.poll();
//        queue.poll();
//        queue.element();

//        System.out.println(queue.peek());
        System.out.println(queue);
    }
}
