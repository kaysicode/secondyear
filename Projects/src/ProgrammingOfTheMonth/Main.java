package ProgrammingOfTheMonth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        // Tawawagin yung method for file
        String message = readFile().toString();

        // decode
        byte[] decodedBytes = Base64.getDecoder().decode(message);
        String decoded = new String(decodedBytes);

        //print
        System.out.println(decoded);

        // Hahanapin yung { sa loob ng string
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(decoded);

        // Find and print the word between curly braces
        if (matcher.find()) {
            String wordBetweenBraces = matcher.group(1);  // Extract the word between { and }
            System.out.println("Word between braces: " + wordBetweenBraces);
        } else {
            System.out.println("No word found between braces.");
        }
    }

    // read the file
    public static StringBuilder readFile() {
        StringBuilder message = new StringBuilder();
        String fileName = "src/ProgrammingOfTheMonth/TEXT 3.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                message.append(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return message;
    }
}
