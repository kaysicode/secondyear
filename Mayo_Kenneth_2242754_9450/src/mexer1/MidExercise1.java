package mexer1;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MidExercise1 extends UnicastRemoteObject implements MidInterface1 {

    public MidExercise1() throws RemoteException {}

    @Override
    public String profileString(String s) throws RemoteException {
        // Convert input string to lowercase
        String newValue = s.toLowerCase();

        // Convert string to char array
        char[] charArr = newValue.toCharArray();

        // Define vowels
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        // StringBuilders to store vowels, consonants, and the final result
        StringBuilder storedVowel = new StringBuilder();
        StringBuilder storedConsonant = new StringBuilder();
        StringBuilder completedResult = new StringBuilder();

        // Loop through each character in charArr
        for (Character letter : charArr) {
            if (Character.isLetter(letter)) { // Only process letters

                // Check if the letter is a vowel
                boolean isVowel = false;
                for (int i = 0; i < vowels.length; i++) {
                    if (letter == vowels[i]) {
                        isVowel = true;
                        break;
                    }
                }

                // Append vowel to storedVowel if it's a vowel, otherwise append to storedConsonant
                if (isVowel) {
                    storedVowel.append(letter);
                } else {
                    storedConsonant.append(letter);
                }
            }
        }

        // Build the completed result by appending vowel and consonant sequences
        completedResult.append(storedVowel).append(storedConsonant);

        // Convert StringBuilder to String
        String convertedVowel = storedVowel.toString();
        String convertedConsonant = storedConsonant.toString();
        String allConverted = completedResult.toString();

        // Return the formatted result
        return "\"" + allConverted.length() + " "  +  convertedVowel + " "  + convertedVowel.length() + " "  +  convertedConsonant + " "  + convertedConsonant.length() + "\"";
    }


    public static void main(String[] args) {
        try {
            MidInterface1 stub = new MidExercise1();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("samplermi", stub);
            System.out.println("Server RMI Established!!");
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }
}
