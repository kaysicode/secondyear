package project.Client.Controller;

import project.Client.ClientRMI;
import project.Client.View.EventPage;
import project.Client.View.SeatPage;
import project.Client.View.WelcomePage;

import javax.swing.*;
import java.rmi.RemoteException;

public class WelcomeControl {
    public static void getActionButton (String action, JFrame frame) throws RemoteException, RuntimeException {
        // Fetch the response to know what action will the getActionButton() method do
        String response = ClientRMI.eventRemote.buttonAction(action);

        if ("GO_TO_EVENT_PAGE".equals(response)) {
            frame.dispose();
            SwingUtilities.invokeLater(EventPage::new);
        } else if ("EXIT_PROGRAM".equals(response)){
            frame.dispose();
            System.exit(0);
        } else if ("BACK_EVENT_PAGE".equals(response)) {
            frame.dispose();
            SwingUtilities.invokeLater(EventPage::new);
        } else if ("GO_TO_WELCOME".equals(response)) {
            frame.dispose();
            SwingUtilities.invokeLater(WelcomePage::new);
        }
    }
}
