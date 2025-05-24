package Generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class trylangers {
    static ArrayList<Integer> numbers = new ArrayList<>();
    static String filePath;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("====================================\n" +
                    "       Choose a Data Set Size\n" +
                    "------------------------------------\n" +
                    "1.  10,000\n" +
                    "2.  50,000\n" +
                    "3.  200,000\n" +
                    "4.  500,000\n" +
                    "5.  1,000,000\n" +
                    "6.  Exit\n" +
                    "====================================\n" +
                    "Please enter your choice: ");

            int dataSetChoice = getIntInput();
            if (dataSetChoice == 6) {
                System.out.println("Exiting...");
                break;
            }

            String dataSetSize = getDataSetSize(dataSetChoice);
            if (dataSetSize == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.println("Choose Case Type:");
            System.out.println("1. Best Case");
            System.out.println("2. Average Case");
            System.out.println("3. Worst Case");
            System.out.print("Enter your choice: ");
            int caseTypeChoice = getIntInput();

            String caseType = getCaseType(caseTypeChoice);
            if (caseType == null) {
                System.out.println("Invalid case choice. Please try again.");
                continue;
            }

            String fullPath = "src/" + dataSetSize + "_" + caseType + "Integer.txt";
            readFile(fullPath);

            System.out.println("Bubble Sort Operations: " + bubbleSort(numbers));
            numbers.clear();
            readFile(fullPath);
            System.out.println("Selection Sort Operations: " + selectionSort(numbers));
            numbers.clear();
            System.out.println();
        }
    }

    static String getDataSetSize(int choice) {
        switch (choice) {
            case 1:
                return "10K_DataSets/10K";
            case 2:
                return "50K";
            case 3:
                return "200K";
            case 4:
                return "500K";
            case 5:
                return "1M";
            default:
                return null;
        }
    }

    static String getCaseType(int choice) {
        switch (choice) {
            case 1:
                return "BestCase";
            case 2:
                return "AverageCase";
            case 3:
                return "WorstCase";
            default:
                return null;
        }
    }

    static int getIntInput() {
        int input = -1;
        while (true) {
            try {
                input = sc.nextInt();
                break; // If input is valid, break out of loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
            }
        }
        return input;
    }

    static int bubbleSort(ArrayList<Integer> numbers) {
        int n = numbers.size();
        boolean swapped;
        int operationCount = 3;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            operationCount += 5;
            for (int j = 0; j < n - 1 - i; j++) {
                operationCount += 2;
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                    swapped = true;
                    operationCount += 4;
                }
            }
            if (!swapped)
                break;
        }
        return operationCount;
    }

    static int selectionSort(ArrayList<Integer> numbers) {
        int n = numbers.size();
        int operationCount = 3;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            operationCount += 3;
            for (int j = i + 1; j < n; j++) {
                operationCount += 2;
                if (numbers.get(j) < numbers.get(minIndex)) {
                    minIndex = j;
                    operationCount++;
                }
            }
            if (minIndex != i) {
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(minIndex));
                numbers.set(minIndex, temp);
                operationCount += 3;
            }
        }
        return operationCount;
    }

    static void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }
}