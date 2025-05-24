import PalindromeApp.Palindrome;
import PalindromeApp.PalindromeHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;

public class PalindClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Palindrome palindrome = PalindromeHelper.narrow(ncRef.resolve_str("Palindrome"));
            boolean response = palindrome.isPalindrome("mom");
            System.out.println("Response from Server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
