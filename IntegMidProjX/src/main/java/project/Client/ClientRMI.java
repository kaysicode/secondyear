package project.Client;

import project.Client.View.LoginPage;
import project.Client.View.VerificationPage;
import project.Interfaces.ClientCallback;
import project.Interfaces.EventInterface;
import project.Interfaces.JSONInterface;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    public static EventInterface eventRemote;
    public static JSONInterface jsonRemote;
    public static ClientCallback clientRemote;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String host = VerificationPage.Verification(); // This now waits for input

            try {
                Registry reg = LocateRegistry.getRegistry(host);
                eventRemote = (EventInterface) reg.lookup("eventRMI");
                jsonRemote = (JSONInterface) reg.lookup("jsonRMI");
                clientRemote = (ClientCallback) reg.lookup("clientRMI");

                // If successful, open LoginPage
                new LoginPage();
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Connection failed: Unable to connect to the server.\nPlease check the IP and try again.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });



    }


}
