import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for managing and processing tournament data.
 * Provides methods to read, filter, sort, and display tournament information.
 * Tournament data is loaded from a CSV file.
 * <pre>
 *     As the name suggested, this is where all of the utilities or methods will be put to process
 * </pre>
 */
@SuppressWarnings("ALL")
public class Utility {
    private static final String FILEPATH = "tournament_data.csv"; // Path to the CSV file containing tournament data.
    private static final List<Tournament> tournament = new ArrayList<>(); // List to store all tournament data where it's in the CSV file
    private static List<Tournament> filtered; // List to store filtered/sorted tournament data. this is where the processed data stored.
    private static final Scanner scan = new Scanner(System.in); // So if the user want to continue or not


    /**
     * Prints a formatted list of tournaments to the console.
     *
     * @param tournamentList the list of tournaments to be printed.
     */
    private static void print(List<Tournament> tournamentList) {

        System.out.printf(
                "%-40s %-20s %-10s %-15s %-15s %-15s %-70s %-15s %-40s%n",
                "Tournament Name", "Tournament Code", "Tier", "Start Date", "End Date",
                "Patch Code", "URL", "Last Update", "CSV Name"
        );
        System.out.println("---------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------");

        for (Tournament tournament : tournamentList) {
            System.out.printf(
                    "%-40s %-20s %-10s %-15s %-15s %-15s %-70s %-15s %-40s%n",
                    tournament.getTournamentName(),
                    tournament.getTournamentCode(),
                    tournament.getTier(),
                    tournament.getStartDate(),
                    tournament.getEndDate(),
                    tournament.getPatchCode(),
                    tournament.getUrl(),
                    tournament.getLastUpdated(),
                    tournament.getCsvName()
            );
        }

        System.out.println("---------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------");
    }

    /**
     * Reads tournament data from a CSV file and populates the {@code tournament} list.
     */
    private static void readFile() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    Utility.class.getClassLoader().getResourceAsStream(FILEPATH)));

            String x;
            String[] data;

            while ((x = br.readLine()) != null) {
                data = x.split(",");

                Tournament processed = new Tournament(data);
                tournament.add(processed);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Exports the list of tournaments to a CSV file.
     * @param tournamentList The list of tournaments to export.
     * @param outputFilePath The output file path.
     */
    public static void exportToCSV(List<Tournament> tournamentList, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Writing the header row
            writer.write("Tournament Name,Tournament Code,Tier,Start Date,End Date,Patch Code,URL,Last Update,CSV Name\n");

            // Writing tournament data
            for (Tournament t : tournamentList) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                        t.getTournamentName(),
                        t.getTournamentCode(),
                        t.getTier(),
                        t.getStartDate(),
                        t.getEndDate(),
                        t.getPatchCode(),
                        t.getUrl(),
                        t.getLastUpdated(),
                        t.getCsvName()));
            }
            System.out.println("Tournament data successfully exported to " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    /**
     * Filters tournaments by name.
     *
     * @param name the name or part of the name to filter tournaments by.
     */
    public static void filterTournamentName(String name) {
        readFile();

        filtered = tournament.stream()
                .filter(t -> t.getTournamentName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No tournaments found matching: " + name);
        } else {
            print(filtered);
            exportToCSV(filtered, "Option1.csv");
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /**
     * Filters tournaments by tier.
     *
     * @param name the tier to filter tournaments by (e.g., "A", "B", "S").
     */
    public static void filterTournamentTier(String name) {
        readFile();

        filtered = tournament.stream()
                .filter(t -> t.getTier().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No tournaments found matching: " + name);
        } else {
            print(filtered);
            exportToCSV(filtered, "Option2.csv");
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /**
     * Sorts tournaments based on the user's choice.
     *
     * @param choice the sorting option:
     *               <ul>
     *                   <li>1: Sort by tournament code in descending order</li>
     *                   <li>2: Sort by tournament name in ascending order</li>
     *                   <li>3: Group and sort tournaments by year</li>
     *               </ul>
     */
    public static void sortTournament(int choice) {
        readFile();

        if (choice == 1) {
            filtered = tournament.stream()
                    .sorted(Comparator.comparing(Tournament::getTournamentCode).reversed())
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("No tournaments found matching the criteria.");
            } else {
                print(filtered);
            }
        } else if (choice == 2) {
            filtered = tournament.stream()
                    .sorted(Comparator.comparing(Tournament::getTournamentName))
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("No tournaments found matching the criteria.");
            } else {
                print(filtered);
                exportToCSV(filtered, "Option3.csv");
            }
        } else if (choice == 3) {
            Map<Integer, List<Tournament>> tournamentsByYear = tournament.stream()
                    .collect(Collectors.groupingBy(t -> {
                        String startDate = String.valueOf(t.getStartDate());
                        return Integer.parseInt(startDate.substring(startDate.length() - 4));
                    }));

            tournamentsByYear.keySet().stream()
                    .sorted()
                    .forEach(year -> {
                        System.out.println("\nTournaments in Year: " + year);
                        List<Tournament> tournamentsForYear = tournamentsByYear.get(year);
                        tournamentsForYear.sort(Comparator.comparing(Tournament::getTournamentName));
                        print(tournamentsForYear);
                    });
        } else {
            System.out.println("Exiting...");
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /**
     * Filters tournaments by the specified month and year.
     *
     * @param month the month to filter tournaments by (1-12).
     * @param year  the year to filter tournaments by.
     */
    public static void filterTournamentDate(int month, int year) {
        readFile();

        filtered = tournament.stream()
                .filter(t -> {
                    String startDate = String.valueOf(t.getStartDate());
                    int tournamentMonth, tournamentYear;
                    if (startDate.length() == 7) {
                        tournamentMonth = Integer.parseInt(startDate.substring(0, 1));
                        tournamentYear = Integer.parseInt(startDate.substring(3, 7));
                    } else if (startDate.length() == 6) {
                        tournamentMonth = Integer.parseInt(startDate.substring(0, 1));
                        tournamentYear = Integer.parseInt(startDate.substring(2, 6));
                    } else if (startDate.length() == 8) {
                        tournamentMonth = Integer.parseInt(startDate.substring(0, 2));
                        tournamentYear = Integer.parseInt(startDate.substring(4, 8));
                    } else {
                        return false;
                    }

                    return tournamentMonth == month && tournamentYear == year;
                })
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No tournaments found for the specified month and year.");
        } else {
            print(filtered);
            exportToCSV(filtered, "Option4.csv");
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /**
     * Searches for a tournament by its tournament code and displays its Tournament Name & URL.
     *
     * @param code the tournament code to search for.
     */
    public static void searchTournamentURL(int code) {
        readFile();

        filtered = tournament.stream()
                .filter(t -> t.getTournamentCode() == code)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No URL found with code");
        } else {
            filtered.forEach(t -> System.out.println(t.getTournamentName() + "\n" + t.getUrl()));
            exportToCSV(filtered, "Option5.csv");
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /**
     * Searches for tournaments by their tournamentcode.
     *
     * @param code the tournament code to search for.
     */
    public static void searchTournamentCode(int code) {
        readFile();

        filtered = tournament.stream()
                .filter(t -> t.getTournamentCode() == code)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No tournaments found with code");
        } else {
            print(filtered);
            exportToCSV(filtered, "Option6.csv");
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /**
     * Filters tournaments by the specified year.
     *
     * @param year the year to filter tournaments by.
     */
    public static void processTournamentYear(int year) {
        readFile();

        filtered = tournament.stream()
                .filter(y -> {
                    String subYear = String.valueOf(y.getStartDate());
                    int tournamentYear;
                    tournamentYear = Integer.parseInt(subYear.substring(subYear.length() - 4));
                    return tournamentYear == year;
                }).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            System.out.println("No tournaments found for the specified month and year.");
        } else {
            System.out.println("Tournaments in the year " + year + " :");
            System.out.println(filtered.size());
            System.out.println();
            print(filtered);
            exportToCSV(filtered, "Option7.csv");
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /**
     * Processes tournaments based on their tier.
     * International tournaments (tier 1) or local tournaments (tier 2) can be filtered and displayed.
     *
     * @param tier the tier to process:
     *             <ul>
     *                 <li>1: International tournaments</li>
     *                 <li>2: Local tournaments</li>
     *             </ul>
     */
    public static void processTournamentTier(int tier) {
        readFile();

        if (tier == 1) {
            List<String> keywords = List.of("world championship", "invitational", "southeast asia", "iesf", "world", "sea games");
            List<String> allowedTiers = List.of("A", "B", "S");

            filtered = tournament.stream()
                    .filter(t -> keywords.stream().anyMatch(keyword -> t.getTournamentName().toLowerCase().contains(keyword)))
                    .filter(t -> allowedTiers.contains(t.getTier().toUpperCase()))
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("No tournaments found for the specified month and year.");
            } else {
                System.out.println("The International Tournaments : ");
                System.out.println("Total Results Found: " + filtered.size());
                System.out.println("5 International Tournament and its details :");

                filtered.stream()
                        .limit(5)
                        .forEach(System.out::println);
            }

        } else if (tier == 2) {
            List<String> keywords = List.of("mpl", "season", "esports", "spring", "tournament", "national", "mdl", "tv");
            List<String> allowedTiers = List.of("A", "B");

            filtered = tournament.stream()
                    .filter(t -> keywords.stream().anyMatch(keyword -> t.getTournamentName().toLowerCase().contains(keyword)))
                    .filter(t -> allowedTiers.contains(t.getTier().toUpperCase()))
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("No tournaments found for the specified month and year.");
            } else {
                System.out.println("The Local Tournaments : ");
                System.out.println("Total Results Found: " + filtered.size());
                System.out.println("5 Local Tournament and its details :");

                filtered.stream()
                        .limit(5)
                        .forEach(System.out::println);
                exportToCSV(filtered, "Option8.csv");
            }
        }
        tournament.clear();
        filtered.clear();
        System.out.println("continue? [y/n]: ");
        String cont = scan.nextLine();

        if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("N")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }
}
