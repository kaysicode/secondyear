package ExamTry;

public class BubbleSortCounter {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};

        System.out.println("Unsorted");
        for (int array : arr) {
            System.out.print(array + " ");
        }

        printBubbleSort(arr);

        System.out.println();
        System.out.println("Sorted");
        for (int array : arr) {
            System.out.print(array + " ");
        }

    }

    private static void printBubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }
}
