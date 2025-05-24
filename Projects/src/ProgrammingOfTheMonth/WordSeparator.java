package ProgrammingOfTheMonth;

public class WordSeparator {
    public static void main(String[] args) {

        Main lobby = new Main();

        String input = lobby.readFile().toString();

        // Convert the input string to an array of words
        String[] words = input.split("\\s+");

        // Iterate through the words and filter out those that are valid (we'll assume the valid words are composed only of alphabetic characters)
        for (String word : words) {
            // Check if the word contains only alphabetic characters
            if (word.matches("[a-zA-Z]+")) {
                System.out.println(word); // Print valid words
            }
        }
    }
}
