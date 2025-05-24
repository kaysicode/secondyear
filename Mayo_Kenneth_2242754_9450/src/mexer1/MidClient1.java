package mexer1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MidClient1 {
    public static void main(String[] args) {

        try {
            // Instantiate a registry, but you will use the getRegistry() method to get the stub in the server.
            Registry reg = LocateRegistry.getRegistry("localhost");
            // After it you will use lookup() method to look up the name tag you established in the server.
            MidInterface1 remote = (MidInterface1) reg.lookup("samplermi");

            Scanner scan = new Scanner(System.in);
            System.out.print("ENTER A WORD HERE: ");
            String test = scan.nextLine();

            // Use the method in the stub of the server as if it's your "remote method".
            System.out.println("Result : " + remote.profileString(test));

        } catch (RemoteException | NotBoundException re) {
            re.printStackTrace();
        }
    }
}
