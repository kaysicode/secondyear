package Midterms;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MidServer {
    public static void main(String[] args) {
        try {
            MidExamA skeletal = new MidServant();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("midEx", skeletal);

            System.out.println("RMI is established!!!");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
