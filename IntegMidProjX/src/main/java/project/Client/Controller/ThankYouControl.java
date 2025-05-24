package project.Client.Controller;
import project.Client.Model.Event;
import project.Client.ClientRMI;
import project.Client.Model.Customer;
import project.Client.View.ProfilePage;

import javax.swing.*;
import java.rmi.RemoteException;

public class ThankYouControl {

    public static String idGenerator(Customer cus) {
        String generateID;
        try {
            generateID = ClientRMI.eventRemote.generateEventID(cus);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        return generateID;
    }

    public static void profile(Customer cus, JFrame frame, Event event) {
        try {
            String response = ClientRMI.eventRemote.buttonAction("GO_TO_PROFILE");
            if ("GO_TO_PROFILE".equals(response)) {
                frame.dispose();
                SwingUtilities.invokeLater(() -> new ProfilePage(cus, event));
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
