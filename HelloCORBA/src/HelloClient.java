import HelloApp.Hello;
import HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Properties;
import java.util.Scanner;

public class HelloClient {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter Server IP: ");
            String ip = scan.nextLine();

            System.out.println("Enter Server port: ");
            String port = scan.nextLine();

            Properties prop = new Properties();
            prop.setProperty("org.omg.CORBA.ORBInitialHost", ip);
            prop.setProperty("org.omg.CORBA.ORBInitialPort", port);

            ORB orb = ORB.init(args, prop);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Hello helloRef = HelloHelper.narrow(ncRef.resolve_str("Hello"));
            String response = helloRef.sayHello("Students");
            System.out.println("Response from Server: " + response);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
