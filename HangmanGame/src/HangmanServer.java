import HangmanApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;

public class HangmanServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            HangmanServant game = new HangmanServant();
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(game);
            HangmanApp.HangmanGame href = HangmanApp.HangmanGameHelper.narrow(ref);

            // Naming service registration
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            ncRef.rebind(ncRef.to_name("HangmanGame"), href);

            System.out.println("Hangman Server ready...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

