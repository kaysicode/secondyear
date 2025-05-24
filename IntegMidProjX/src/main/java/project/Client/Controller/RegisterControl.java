package project.Client.Controller;
import project.Client.ClientRMI;
import project.Client.Model.Event;
import project.Client.View.PasswordPage;
import project.Client.View.SeatPage;
import project.Interfaces.ClientInterface;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RegisterControl extends UnicastRemoteObject implements ClientInterface {

    public RegisterControl() throws RemoteException{}

    public static void submitChecker(String fName, String lName, String contNo, String email,
                                     JFrame frame, Event event, int seatNo) {
        try {
            boolean isValid = ClientRMI.eventRemote.registerTxtBoxSubmitted(fName, lName, contNo, email, frame, event);

            if (!isValid) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Input! Please check your information.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String eID = event.eventID();
            String[] cusData = {lName, fName, contNo, email, eID};



            String name = fName + " " + lName;

            RegisterControl regControl = new RegisterControl();

            ClientRMI.clientRemote.registerClient(name, (ClientInterface) regControl);

            frame.dispose();
            SwingUtilities.invokeLater(() -> new PasswordPage(cusData, seatNo, event));

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getOnlineCustomers() {
        List<String> onCus = new ArrayList<>();
        try {
             onCus = ClientRMI.clientRemote.getOnlineClients();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        return onCus;
    }

    public static void backBtn(String action, JFrame frame, Event event, int seatNumber) {
        // Fetch the response to know what action will the getActionButton() method do
        String response;
        try {
            Integer selectedSeat = seatNumber;
            if (selectedSeat != null) {
                ClientRMI.clientRemote.removeSeat(selectedSeat); // Custom method you implement in server
            }
            response = ClientRMI.eventRemote.buttonAction(action);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        if ("BACK_SEAT_PAGE".equals(response)) {
            frame.dispose();
            SwingUtilities.invokeLater(() -> new SeatPage(event));
        }
    }

    @Override
    public void updateClientList(List<String> clients) throws RemoteException {
        SwingUtilities.invokeLater(() -> {
            ProfileControl.listModel.clear();
            for (String client :  clients){
                ProfileControl.listModel.addElement(client);
            }
        });
    }
}
