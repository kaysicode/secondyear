package pexer1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PreExercise1 {
    public static void main(String[] args) {
        // Scanner input for the user
        Scanner scan = new Scanner(System.in);

        String continuePa; // String to check if the user still want to continue
        int host = 1; // counter

        // try - catch clause for the UnknownHost that user will give
        try {
            do {
                System.out.println(); // space
                System.out.print("Host " + host + " - Type IP address/Hostname: ");
                String address = scan.nextLine();

                // get the all possible name such as mac address and so on and insert it into the Array
                // with InetAddress as datatype
                InetAddress[] ipAddresses = InetAddress.getAllByName(address);
                System.out.println("Number of Hosts/IPs:" + ipAddresses.length);  // length of the array

                System.out.println("Host name \t IP Address");
                // for each to get the host address in the array
                for (InetAddress ip : ipAddresses) {
                    System.out.println("\t" + address + "\t" + ip.getHostAddress());
                }

                // ask user if he want to continue
                System.out.print("Search another [y/n]? ");
                continuePa = scan.nextLine();

                host++;
            } while (continuePa.equalsIgnoreCase("Y"));
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
    }
}
