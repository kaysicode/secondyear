package BroCodeDSA.Structure;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Collections;

public class PriorityQueueMain {
    public static void main(String[] args) {


        //Priority Queue = A FIFO data structure that serves elements
        //                 with the highest priorities first
        //                 before elements with lower priority

        Queue<String> queue = new PriorityQueue<>(Collections.reverseOrder());

        queue.offer("B");
        queue.offer("A");
        queue.offer("S");
        queue.offer("F");
        queue.offer("A+");

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
