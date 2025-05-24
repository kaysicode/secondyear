import HelloApp.Hello;
import HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class HelloServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            HelloImpl helloImp = new HelloImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImp);
            Hello href = HelloHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = (NamingContextExt) NamingContextHelper.narrow(objRef);
            ncRef.rebind(ncRef.to_name("Hello"), href);

            System.out.println("Server is ready and waiting...");
            orb.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
