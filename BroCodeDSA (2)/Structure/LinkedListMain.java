package BroCodeDSA.Structure;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListMain {
    public static void main(String[] args) {

        // ****************************************************************************************

        // LinkedList = stores Nodes in 2 parts (data + address)
        //              Nodes are in non-consecutive memory locations
        //              Elements are linked using pointers

        //                      Singly Linked list
        //          Node                Node                  Node
        //      [data | address] -> [data | address] -> [data | address]

        //                      Doubly Linked list
        //                 Node                           Node
        //      [address | data | address] <-> [address | data | address]

        //          advantages?
        //          1. Dynamic Data BroCodeDSA.Structure (allocates needed memory while running)
        //          2. Insertion and Deletion of nodes is easy. 0(1)
        //          3. No/Low memory waste

        //          disadvantages?
        //          1. Greater memory usage (additional pointer)
        //          2. No random access of elements (no index [i]
        //          3. Accessing/searching elements is more time-consuming. 0(n)

        //          uses?
        //          1. implements Stacks/Queues
        //          2. GPS navigation
        //          3. music playlist

        // ****************************************************************************************

        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        //LinkedList can mimic Stack and Queue

        //A stack mimic
        /*
        linkedList.push("A");
        linkedList.push("B");
        linkedList.push("C");
        linkedList.push("D");
        linkedList.push("F");
        */

        //A queue mimic

        linkedList.offer("A");
        linkedList.offer("B");
        linkedList.offer("C");
        linkedList.offer("D");
        linkedList.offer("F");

//        linkedList.poll();

        linkedList.add(4, "E");
        linkedList.remove(4);


        int sum = 0;
        for (int i = 0; i < 10 ; i++) {
            sum += i;
        }
        System.out.println(sum);

        System.out.println(linkedList);
    }
}
