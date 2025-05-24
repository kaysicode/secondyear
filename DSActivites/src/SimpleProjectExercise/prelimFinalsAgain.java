package SimpleProjectExercise;

import java.util.Scanner;

public class prelimFinalsAgain {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run() {
        byte choice = 0;
        while (choice != 9) {
            menu();
            choice = readChoice(choice);
        }
    }

    public static void menu() {

        System.out.println("""
                ----------------MENU----------------
                1. Determine if  a number is a Perfect, Abundant, Deficient number
                2. Income Tax Computation
                3. Inserting an Element in an array
                4. Deleting an Element in an array
                5. Display the Multiplication table using 2d arrays
                6. Remove all vowels in word
                7. Remove duplicate word
                8. Exit the program...""");
        System.out.print("Enter here (1-8) : ");
    }

    public static byte readChoice(byte ch) {
        boolean validInput = false;
        byte med = 0;
        try {
            ch = (byte) Integer.parseInt(scan.next());
            while (!validInput) {
                if (med > ch) {
                    System.out.print("You input higher value, input again <1 - 8> : ");
                } else if (ch < med) {
                    System.out.print("You input lower value, input again <1 - 8> :");
                } else {
                    validInput = true;
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("You enter an non-integer value. input again <1 - 8> : ");
        }
        return ch;
    }
}
