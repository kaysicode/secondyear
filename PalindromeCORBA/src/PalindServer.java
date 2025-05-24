import PalindromeApp.Palindrome;
import PalindromeApp.PalindromeHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class PalindServer {
    public static void main(String[] args) {
        try {
            // Server Activation
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Implementation Method
            PalindImpl palind = new PalindImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(palind);
            Palindrome palindrome = PalindromeHelper.narrow(ref);

            // Rebind
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = (NamingContextExt) NamingContextHelper.narrow(objRef);
            ncRef.rebind(ncRef.to_name("Palindrome"), palindrome);

            System.out.println("Server Started and Waiting...");
            orb.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
