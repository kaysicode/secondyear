package BroCodeDSA.Algorithm;

import org.w3c.dom.ls.LSOutput;

public class SelectionSortAlgo {
    public static void main(String[] args) {

        // Selection sort = search through an array and keep track of the minimum value during
        //                  each iteration. at the end of each iteration, we swap variables.

        //                  Quadratic time O(n^2)
        //                  small data set = okay
        //                  large data set = BAD

        int[] array = {3, 2, 1};

        selectionSort(array);

        for (int i : array) {
            System.out.print(i + " ");
        }


    }

    private static void selectionSort(int[] array) {
        int n = array.length; // 1 Assignment
        int swap = 0;
        boolean swapped;

        int operations = 2;
        for (int i = 0; i < n - 1; i++) { // 2n + 1
            swapped = false; // n
            int min = i; // n
            operations += 4;
            for (int j = i + 1; j < n; j++) { //2n
                operations += 3;
                if (array[min] > array[j]) {  // n
                    min = j; //n;
                    swapped = true; // Assignment
                    operations += 2;
                }
            }
            int temp = array[i]; // n
            array[i] = array[min]; // n
            array[min] = temp; // n
            swap++;
            operations += 4;

            if (!swapped) {
                break;
            }
        }

        System.out.println("operations : " + operations);
        System.out.println("swaps : " + swap);
    }
}
