package Generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class Generator200k {
    public static void main(String[] args) {
        String bestCase = "src/Generator/200k-(BestCase)";
        String averageCase = "src/Generator/200k-(AverageCase)";
        String worstCase = "src/Generator/200k-(WorstCase)";

        int operations = 0;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(worstCase));

            for (int i = 2000000; i > 0; i--) {
                bw.write(Integer.toString(i));
                bw.newLine();
                operations++;
            }

            System.out.println("Total Operations : " + operations);
            bw.close();


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
