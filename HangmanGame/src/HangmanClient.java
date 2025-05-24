import HangmanApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.Scanner;

public class HangmanClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            HangmanApp.HangmanGame game = HangmanApp.HangmanGameHelper.narrow(ncRef.resolve_str("HangmanGame"));

            Scanner sc = new Scanner(System.in);
            while (!game.isGameOver()) {
                System.out.println(game.getWordProgress());
                System.out.print("Guess a letter: ");
                String guess = sc.nextLine();
                System.out.println(game.guessLetter(guess));
            }

            System.out.println(game.getWordProgress());
            System.out.println(game.isWin() ? "You Win!" : "Game Over!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

