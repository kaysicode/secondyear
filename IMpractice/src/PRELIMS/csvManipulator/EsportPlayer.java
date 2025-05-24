package PRELIMS.csvManipulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EsportPlayer implements Comparable<EsportPlayer> {
    private String team,linePos,status,ign;
    private static final String FILEPATH = "src/PRELIMS/csvManipulator/Msc_Content - Team.csv";

    public EsportPlayer (String[] data) {
        this.team = data[0];
        this.linePos = data[1];
        this.status = data[2];
        this.ign = data[3];
    }

    public String getTeam() {
        return team;
    }

    public String getLinePos() {
        return linePos;
    }

    public String getStatus() {
        return status;
    }

    public String getIgn() {
        return ign;
    }


    public static void teamSort() {
        List<EsportPlayer> team = new ArrayList<>();
        List<EsportPlayer> sorted;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            br.readLine();
            String x;
            String[] teams;

            while((x = br.readLine()) != null) {
                teams = x.split(",");

                EsportPlayer ePlay = new EsportPlayer(teams);
                team.add(ePlay);
            }

            sorted = team.stream()
                    .sorted()
                    .toList();

            print(sorted);



        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void linePosSort() {
        List<EsportPlayer> teams = new ArrayList<>();
        List<EsportPlayer> sorted;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            br.readLine();
            String x;
            String[] sorted_LinePos;

            while ((x = br.readLine()) != null ) {
                sorted_LinePos = x.split(",");

                EsportPlayer ePlay = new EsportPlayer(sorted_LinePos);
                teams.add(ePlay);

            }

            sorted = teams.stream()
                    .sorted(Comparator.comparing(EsportPlayer::getLinePos))
                    .collect(Collectors.toList());

            print(sorted);



        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void filterLinePos(String role){
        List<EsportPlayer> teams = new ArrayList<>();
        List<EsportPlayer> sorted;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            br.readLine();
            String x;
            String[] filtered_LinePos;

            while ((x = br.readLine()) != null) {
                filtered_LinePos = x.split(",");
                EsportPlayer ePlay = new EsportPlayer(filtered_LinePos);

                teams.add(ePlay);
            }

            sorted =  teams.stream()
                    .filter(ep -> ep.getLinePos().equalsIgnoreCase(role))
                    .collect(Collectors.toList());

            print(sorted);



        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void searchPlayerStatus(String player) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            br.readLine();
            String x;
            String[] filtered_LinePos;
            boolean isFound = false;

            while ((x = br.readLine()) != null) {
                filtered_LinePos = x.split(",");

                if (filtered_LinePos.length > 3 && filtered_LinePos[3].equalsIgnoreCase(player)) {
                    System.out.println("Status : " + filtered_LinePos[2]);
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("No matching data found.");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void print(List<EsportPlayer> players) {

        System.out.printf("%-20s %-20s %-15s %-15s%n", "Team", "Line Position", "Status", "Player");
        System.out.println("-----------------------------------------------------------------");

        // Print sorted list
        for (EsportPlayer player : players) {
            System.out.printf("%-20s %-20s %-15s %-15s%n",
                    player.getTeam(),
                    player.getLinePos(),
                    player.getStatus(),
                    player.getIgn());
        }

    }


    @Override
    public int compareTo(EsportPlayer o) {
        return this.team.compareToIgnoreCase(o.team);
    }
}