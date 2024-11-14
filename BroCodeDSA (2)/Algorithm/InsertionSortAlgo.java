package BroCodeDSA.Algorithm;

import java.util.ArrayList;

public class InsertionSortAlgo {
    public static void main(String[] args) {
        // Insertion sort = after comparing elements to the left
        //                  shir\ft elements to the right to make room to insert a value

        //                  Quadratic time O(n^2)
        //                  small data set = decent
        //                  large data set = BAD

        //                  it's less step than Bubble sort
        //                  Best case is O(n) compared to Selection Sort O(n^2)



        ArrayList<Integer> al = new ArrayList<>();
        al.add(3);
        al.add(2);
        al.add(1);

        insertionSort(al);

        for (int i : al) {
            System.out.print(i + " ");
        }
    }

    private static void insertionSort(ArrayList<Integer> al) {
        int n = al.size();
        int swapCounts = 0;

        int operations = 2;
        for(int i = 1; i < n; i++) { // 2n + 1
            int temp = al.get(i); // n
            int j = i -1; // n
            operations += 6;

            while(j >= 0 && al.get(j) > temp) { // n
                al.set(j + 1, al.get(j)); // n
                j--; // n
                operations += 4;
                swapCounts++;
            }
            operations++;
            al.set(j + 1, temp); // n
        }

        System.out.println("operations : " + operations);
        System.out.println("swaps : " + swapCounts);
    }
}
