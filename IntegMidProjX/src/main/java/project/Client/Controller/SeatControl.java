package project.Client.Controller;

import project.Client.ClientRMI;
import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Client.Model.Links;
import project.Client.View.RegisterPage;
import project.Client.View.SeatPage;
import project.Client.View.WelcomePage;
import project.Interfaces.SeatInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatControl extends UnicastRemoteObject implements SeatInterface {
    private static Map<Integer, JButton> seatButtons = new HashMap<>(); // to track seat buttons easily
    public SeatControl() throws RemoteException{}

    public static void updateSeatAvailability(Event event, JPanel panel) {
        try {
            List<Customer> cusList = ClientRMI.jsonRemote.readToJSON(
                    Links.CUSTOMER_JSON.getFilePath(), // Use the Enum Instead of Hardcoded Path
                    Customer.class
            );
            List<Integer> takenSeats = ClientRMI.eventRemote.getUpdatedTakenSeats(cusList, event);
            System.out.println("Taken seats: " + takenSeats);
            for (Component component : panel.getComponents()) {
                if (component instanceof JButton) {
                    JButton seatBtn = (JButton) component;
                    int seatNumber = Integer.parseInt(String.valueOf(seatBtn.getText()));

                    if (takenSeats.contains(seatNumber)) {
                        seatBtn.setBackground(Color.RED);
                    } else {
                        seatBtn.setBackground(Color.LIGHT_GRAY);
                    }
                    seatBtn.repaint();
                    seatBtn.revalidate();
                }
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static void seatValidation(int s, JButton but, JFrame frame, ActionEvent button, Event event, SeatPage seatPage) {
        JButton clickedButton = (JButton) button.getSource();
        int clickedSeat = Integer.parseInt(clickedButton.getText());

        // Sync latest taken seats from server
        List<Integer> seatTaken = getSeatList();
        for (Integer seatNum : seatTaken) {
            if (SeatControl.seatButtons.containsKey(seatNum)) {
                SeatControl.seatButtons.get(seatNum).setBackground(Color.RED);
            } else  {
                SeatControl.seatButtons.get(seatNum).setBackground(Color.LIGHT_GRAY);
            }
        }
        // Check again after syncing
        if (seatTaken.contains(clickedSeat) || clickedButton.getBackground().equals(Color.RED)) {
            JOptionPane.showMessageDialog(null, "This Seat is already Taken", "Taken Seat",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            // Set color locally
            clickedButton.setBackground(Color.RED);

            // Notify others
            seatBroadcast(s);

            if (seatPage != null) {
                seatPage.setSelectedSeat(clickedSeat); // Set selected seat in SeatPage
            }

            frame.dispose();

            // Proceed to Register
            SwingUtilities.invokeLater(() -> new RegisterPage(clickedSeat, event));
        }
    }

    public static List<Integer> getSeatList() {
        List<Integer> seatTaken = new ArrayList<>();
        try {
            seatTaken = ClientRMI.clientRemote.getSeat();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        return seatTaken;
    }

    public static void seatBroadcast(int seatNumber) {
        try {
            SeatControl seatControl = new SeatControl();
            ClientRMI.clientRemote.registerSeat(seatNumber, seatControl);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public static void registerSeatButton(int seatNumber, JButton button) {
        seatButtons.put(seatNumber, button);
    }

    @Override
    public void updateSeatList(List<Integer> seat) throws RemoteException {
        for (Integer seatNumber : seat) {
            System.out.println(seatNumber);
            JButton button = seatButtons.get(seatNumber);
            if (button != null) {
                SwingUtilities.invokeLater(() -> button.setBackground(Color.RED));
            }
        }

    }

}
