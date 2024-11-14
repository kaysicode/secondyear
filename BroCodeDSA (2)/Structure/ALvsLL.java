package BroCodeDSA.Structure;

import java.util.ArrayList;
import java.util.LinkedList;

public class ALvsLL {
    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        long startTime;
        long endTime;
        long elapsedTime;

        for (int i = 0; i < 1000000; i++) {
            linkedList.add(i);
            arrayList.add(i);
        }


        // **************** Linked List ****************
        startTime = System.nanoTime(); // it will give back the nanoseconds of the system (milliseconds)

        // do something

        // Accessing a element
//        linkedList.get(0); // in the head of the list
//        linkedList.get(500000); // in the middle of the list
//        linkedList.get(999999); // in the end of the list

        // Removing a element
//        linkedList.remove(0); // start
//        linkedList.remove(500000); // middle
        linkedList.remove(999999); // last

        endTime = System.nanoTime();

        elapsedTime = endTime - startTime;

        System.out.println("LinkedList:\t" + elapsedTime + " ns");


        // **************** Array List *****************

        startTime = System.nanoTime(); // it will give back the nanoseconds of the system (milliseconds)

        // do something

        // Accessing a element
//        arrayList.get(0); // in the head of the array
//        arrayList.get(500000); // in the middle of the array
//        arrayList.get(999999); // in the end of the array

        // Removing a element
//        arrayList.remove(0); // start
//        arrayList.remove(500000); // middle
        arrayList.remove(999999); // last

        endTime = System.nanoTime();

        elapsedTime = endTime - startTime;

        System.out.println("ArrayList:\t" + elapsedTime + " ns");
    }
}
