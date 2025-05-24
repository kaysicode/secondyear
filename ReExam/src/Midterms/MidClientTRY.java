package Midterms;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MidClientTRY {
    public static void main(String[] args) {
        try {
            Registry stub = LocateRegistry.getRegistry();
            MidExamA remote = (MidExamA) stub.lookup("midEx");

            System.out.println("Result 1a : " + remote.sumResult(1, 9)); // Expected Answer: 20
            System.out.println("Result 1b : " + remote.sumResult(-9, 5)); // Expected Answer: 6
            System.out.println("Result 1c : " + remote.sumResult(20, 5)); // Expected Answer: 0
            System.out.println();
            System.out.println("Result 2a : " + remote.digitalRoot(29)); // Expected Answer: 5
            System.out.println("Result 2b : " + remote.digitalRoot(36)); // Expected Answer: 6
            System.out.println("Result 2c : " + remote.digitalRoot(45)); // Expected Answer: 6
            System.out.println();
            System.out.println("Result 2a : " + remote.digitalRoot2(29)); // Expected Answer: 5
            System.out.println("Result 2b : " + remote.digitalRoot2(36)); // Expected Answer: 6
            System.out.println("Result 2c : " + remote.digitalRoot2(45)); // Expected Answer: 6
            System.out.println();
            System.out.println("Result 2a : " + remote.digitalRootNiRobe(29)); // Expected Answer: 5
            System.out.println("Result 2b : " + remote.digitalRootNiRobe(36)); // Expected Answer: 6
            System.out.println("Result 2c : " + remote.digitalRootNiRobe(45)); // Expected Answer: 6

        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
