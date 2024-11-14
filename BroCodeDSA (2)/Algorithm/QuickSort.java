package BroCodeDSA.Algorithm;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {23, 74, 2, 123, 83, 65, 88, 15, 22};
        int size = array.length;

        System.out.println("Before Sorting");
        printArray(array, size);

        quickSort(array, 0, size - 1);
        System.out.println();

        System.out.println("After Sorting");
        printArray(array, size);
    }

    static void printArray(int[] array, int size) {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(array[i] + " ");
        }
    }

    static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int p = partition(array, start, end);
            quickSort(array, start, p - 1);
            quickSort(array, p + 1, end);
        }
    }

    static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    static void swap (int[] array, int x, int y) {
        int i = array [x];
        array[x] = array[y];
        array[y] = i;
    }
}
