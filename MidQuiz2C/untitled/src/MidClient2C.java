import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MidClient2C {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry();
            MidInterface2C remote = (MidInterface2C) reg.lookup("midquiz");

            System.out.println("sending (1, 25, 2020)...");
            System.out.println("expecting result: Saturday");
            System.out.println(remote.dotw(1, 25, 2020));

        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }


    }
}
