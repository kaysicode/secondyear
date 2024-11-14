package BroCodeDSA.Algorithm;

public class BubbleSortAlgo {
    public static void main(String[] args) {
        // Bubble Sort Algorithm used quadratic function
        // quadratic - 0(n^2)

        // Bubble Sort = pairs of adjacent elements are compared, and the elements
        //               swapped if they are not in order

        //                Quadratic time O(n^2)
        //                small data set = okay-ish
        //                large data set = BAD (plz don't)

        int[] array = {8, 7, 9, 2, 3, 1, 5, 4, 6};

        bubbleSort(array);

        for (int i : array) {
            System.out.print(i + " ");
        }

    }
    public static void bubbleSort(int[] arr) {
        int n = arr.length; //1 Assignment
        int swap = 0; // 2 Assignment
        boolean swapped;

        int operations = 2;
        for (int i = 0; i < n - 1; i++) { //2n + 1
            swapped = false; // n
            operations += 4;
            for (int j = 0; j < n - i - 1; j++) { //2n
                operations += 3;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; // n
                    arr[j] = arr[j + 1]; // n
                    arr[j + 1] = temp; // n
                    swap++;
                    swapped = true; // n
                    operations += 4;
                }
            }
            operations++;
            if (!swapped) {
                break;
            }
        }
        System.out.println("Operations : " + operations);
        System.out.println("swap : " + swap);

    }
}
