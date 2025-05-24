import java.util.HashSet;
import java.util.Set;

public class HangmanServant extends HangmanApp.HangmanGamePOA {
    private String word = "banana";
    private StringBuilder progress = new StringBuilder("______");
    private int lives = 6;
    private Set<Character> guessed = new HashSet<>();


    @Override
    public String guessLetter(String letter) {
        if (letter.length() != 1) return "invalid input.";
        char ch = Character.toLowerCase(letter.charAt(0));
        if (guessed.contains(ch)) return "Already guessed";

        guessed.add(ch);
        boolean correct = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                progress.setCharAt(i, ch);
                correct = true;
            }
        }
        if (!correct) lives--;
        return correct ? "Correct!" : "Wrong!";
    }

    @Override
    public String getWordProgress() {
        return progress.toString() + " | Lives: " + lives;
    }

    @Override
    public boolean isGameOver() {
        return lives <= 0 || progress.toString().equals(word);
    }

    @Override
    public boolean isWin() {
        return progress.toString().equals(word);
    }
}
