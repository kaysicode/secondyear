package PRELIMS.csvManipulator;

import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {

        Scanner scan =  new Scanner(System.in);
        menu();
        System.out.print("Enter here : ");
        int choice =  Integer.parseInt(scan.nextLine());

        switch (choice) {
            case 1 -> EsportPlayer.teamSort();
            case 2 -> EsportPlayer.linePosSort();
            case 3 -> filterMenu_LinePos();
            case 4 -> search();
            default -> System.out.println("Invalid choice.");
        }

        
    }

    static void menu() {
        System.out.println("""
                -----------MENU-----------
                1. Sort per Teams
                2. Sort per Line Position
                3. Filter a Line Position
                4. Search Player's Status
                --------------------------""");
    }

    static void filterMenu_LinePos() {
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                ----------------------
                1. Jungler
                2. Exp
                3. Gold Lane
                4. Roamer
                5. Middle
                6. Flex
                7. Coach
                8. Analisis""");
        System.out.print("Enter Here: ");
        int filter = Integer.parseInt(scan.nextLine());

        switch (filter) {
            case 1 -> EsportPlayer.filterLinePos("Jungler");
            case 2 -> EsportPlayer.filterLinePos("Exp");
            case 3 -> EsportPlayer.filterLinePos("Gold Lane");
            case 4 -> EsportPlayer.filterLinePos("Roamer");
            case 5 -> EsportPlayer.filterLinePos("Middle");
            case 6 -> EsportPlayer.filterLinePos("Flex");
            case 7 -> EsportPlayer.filterLinePos("Coach");
            case 8 -> EsportPlayer.filterLinePos("Analisis");
            default -> System.out.println("Invalid choice!");

        }
    }

    static void search() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Player's IGN : ");
        String playerIGN = scan.nextLine();

        EsportPlayer.searchPlayerStatus(playerIGN);


    }
}
