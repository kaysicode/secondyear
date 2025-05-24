package project.Client.Controller;
import project.Client.ClientRMI;
import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Client.Model.Links;
import project.Client.View.ThankYouPage;

import javax.swing.*;
import java.rmi.RemoteException;

public class PasswordControl {
    public static void passChecker(JFrame frame, String pass1, String pass2, String[] data, int seat, JRadioButton male, JRadioButton female, Event event) {
        try {
            boolean isSame = ClientRMI.eventRemote.passwordConfirmation(pass1, pass2);

            if (!isSame) {
                JOptionPane.showMessageDialog(frame,
                        "Your Password aren't the same!!!!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String gender = "";
            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a gender.");
                return;
            }

            Customer cus = new Customer(data, seat, pass1, gender);
            ClientRMI.jsonRemote.writeToJSON(
                    Links.CUSTOMER_JSON.getFilePath(),
                    cus);

            JOptionPane.showMessageDialog(frame, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            SwingUtilities.invokeLater(() -> new ThankYouPage(cus, event));

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

}
