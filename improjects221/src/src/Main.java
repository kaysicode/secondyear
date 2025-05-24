import java.util.Scanner;

/**
 * Main.java this is where the menu and user will be entering.
 * <pre>
 *     This is the class where it's executable
 * </pre>
 */
@SuppressWarnings("ALL")
public class Main {
    /**
     * main method
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        explanation();
        System.out.println("\nPress [ENTER] to continue...");
        scan.nextLine();

        while (true) {
            menu();
            System.out.print("Enter here: ");

            int choice;
            try {
                choice = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue; // Return to the menu
            }

            switch (choice) {
                case 1 -> filterMenu(scan);
                case 2 -> filterTier(scan);
                case 3 -> sortMenu(scan);
                case 4 -> filterDate(scan);
                case 5 -> uRLCode(scan);
                case 6 -> tournamentCode(scan);
                case 7 -> processYear(scan);
                case 8 -> processTournament(scan);
                case 9 -> {
                    System.out.println("Exiting the program...");
                    scan.close();
                    return; // Exit the program
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    /**
     * A Explanation of the data so that people could know if ever they are using the program
     */
    static void explanation() {
        System.out.println("""
                =============================================WELCOME==============================================================
                This CSV file contains information about MLBB (Mobile Legends: Bang Bang) tournaments held over the years.
                It includes several data points, some of which may require explanation. Here's a breakdown of each field:
                Tournament Code - A numerical code that identifies each tournament, with the highest possible code
                                representing the most recent tournament. The range starts from 1 (the highest) and goes up to 228.
                Tournament Name - The official name of the tournament.
                Tiers - MLBB tournaments are categorized into three tiers:
                       - S: The highest tier, representing top-tier tournaments.
                       - A: Mid-level tournaments.
                       - B: The lowest tier, typically smaller tournaments.
                Start Date - The date when the tournament begins.
                End Date - The date when the tournament ends.
                Patch Code - The version of the MLBB game used during that specific tournament.
                URL - A link to the tournament's Wikipedia page for more detailed information.
                Last Updated - The date (day, month, and year) when the CSV file was last updated.
                CSV Name - The name of the CSV file containing the tournament data.
                ====================================================================================================================""");
    }

    /**
     * menu method, where this is all of our Information Requirements
     */
    static void menu() {
        System.out.println("""
                =========================================== MENU ===========================================
                1. Filter/Search tournaments by name:
                   - Enter a partial match or keywords to search for tournaments easily.                                
                2. Filter tournaments by tier:
                   - Choose from a range of tiers, from B to S, where S is the highest tier.                                
                3. Sort tournaments:
                   - Access a submenu to sort tournaments based on various criteria by selecting an option.                                
                4. Filter tournaments by start date:
                   - Display tournaments that begin on or after a specified date.                               
                5. Search for the tournament URL using its code:
                   - Input a tournament code to retrieve its URL.                                
                6. Search for a tournament by its code:
                   - Enter a specific tournament code to find details.                   
                7. Process the number of tournaments held in a specific year:
                   - Enter a year to calculate how many tournaments occurred during that time.
                8. Process how many local or international tournament:
                    - Access a submenu to choose if local or international tournament and count and also filter it.
                      And give 5 tournaments
                9. Exit the program
                -------------------------------------------------------------------------------------------------""");
    }

    /**
     * menu method, where this is all of our Information Requirements
     */
    static void filterMenu(Scanner scan) {
        System.out.println("""
                ------------CHOOSE-------------
                1. MPL
                2. World Championship
                3. Southeast Asia Cup
                4. Search a tournament
                5. Exit
                -------------------------------""");
        System.out.print("Enter here: ");
        int choice = Integer.parseInt(scan.nextLine());

        switch (choice) {
            case 1 -> Utility.filterTournamentName("MPL");
            case 2 -> Utility.filterTournamentName("World Championship");
            case 3 -> Utility.filterTournamentName("Southeast Asia Cup");
            case 4 -> {
                System.out.print("Enter a tournament: ");
                String search = scan.nextLine();
                Utility.filterTournamentName(search);
            }
            case 5 -> System.out.println("Exiting submenu...");
            default -> System.out.println("Invalid input!");
        }
    }

    /**
     * fiterTier method, whereas this is the submenu of option 2 in the main menu.
     */
    static void filterTier(Scanner scan) {
        System.out.println("""
                ---CHOOSE---
                1. S
                2. A
                3. B
                4. Exit
                ------------""");
        System.out.print("Enter here: ");
        String choice = scan.nextLine();

        switch (choice.toUpperCase()) {
            case "1", "S" -> Utility.filterTournamentTier("S");
            case "2", "A" -> Utility.filterTournamentTier("A");
            case "3", "B" -> Utility.filterTournamentTier("B");
            case "4", "EXIT" -> System.out.println("Exiting submenu...");
            default -> System.out.println("Wrong input!");
        }
    }

    /**
     * sortMenu method, whereas this is the submenu of option 3 in the main menu.
     */
    static void sortMenu(Scanner scan) {
        System.out.println("""
                ------------------CHOOSE----------------------
                1. Sort the Tournaments Code reversely
                2. Sort the Tournaments Name alphabetically
                3. Sort the Tournaments each year
                4. Exit
                --------------------------------------------""");
        System.out.print("Enter here: ");
        int choice = Integer.parseInt(scan.nextLine());
        Utility.sortTournament(choice);
    }

    /**
     * filterDate method, whereas this is the submenu of option 4 in the main menu.
     */
    static void filterDate(Scanner scan) {
        int month, year;
        while (true) {
            System.out.print("Enter a month <1-12>: ");
            month = Integer.parseInt(scan.nextLine());
            System.out.print("Enter a year <2018-2023>: ");
            year = Integer.parseInt(scan.nextLine());
            if (month >= 1 && month <= 12 && year >= 2018 && year <= 2023) break;
            System.out.println("Invalid input. Try again.");
        }
        Utility.filterTournamentDate(month, year);
    }

    /**
     * uRLCode method, whereas this is the submenu of option 5 in the main menu.
     */
    static void uRLCode(Scanner scan) {
        System.out.print("Enter Code of Tournament to find its URL: ");
        int code = Integer.parseInt(scan.nextLine());
        Utility.searchTournamentURL(code);
    }

    /**
     * tournamentCode method, whereas this is the submenu of option 6 in the main menu.
     */
    static void tournamentCode(Scanner scan) {
        System.out.print("Enter Code of Tournament to get its details: ");
        int code = Integer.parseInt(scan.nextLine());
        Utility.searchTournamentCode(code);
    }

    /**
     * processYear method, whereas this is the submenu of option 7 in the main menu.
     */
    static void processYear(Scanner scan) {
        System.out.print("Enter a year (2018-2023): ");
        int year = Integer.parseInt(scan.nextLine());
        Utility.processTournamentYear(year);
    }

    /**
     * processTournament method, whereas this is the submenu of option 8 in the main menu.
     */
    static void processTournament(Scanner scan) {
        System.out.println("""
                -------------CHOOSE-------------
                1. International Tournaments
                2. Local Tournaments
                -------------------------------""");
        System.out.print("Enter here: ");
        int tournament = Integer.parseInt(scan.nextLine());
        Utility.processTournamentTier(tournament);
    }
}
