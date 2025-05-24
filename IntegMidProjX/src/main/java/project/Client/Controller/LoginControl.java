package project.Client.Controller;

import project.Client.ClientRMI;
import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Client.Model.Links;
import project.Client.View.ProfilePage;
import project.Exceptions.AlreadyLoggedInException;
import project.Exceptions.UserExistsException;
import project.Interfaces.ClientInterface;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LoginControl extends UnicastRemoteObject implements ClientInterface {
    private static List<String> onlineCustomers = new ArrayList<>();
    public LoginControl() throws RemoteException{}

    public static void checkAccount(String name, String pass, JFrame frame) {
        try {
            List<Customer> customerList = ClientRMI.jsonRemote.readToJSON(
                    Links.CUSTOMER_JSON.getFilePath(),
                    Customer.class
            );

            List<Event> eventList = ClientRMI.jsonRemote.readToJSON(
                    Links.EVENT_JSON.getFilePath(),
                    Event.class
            );

            // Try login
            Customer cusRes = ClientRMI.clientRemote.loginCall(customerList, name, pass);

            if (cusRes != null) {
                // Now safe to call eventID()
                Event eventRes = eventList.stream().
                        filter(e -> e.eventID().equals(cusRes.eventID()))
                        .findFirst().orElse(null);

                if (eventRes != null) {

                    String fullName = cusRes.firstName() + " " + cusRes.lastName();

                    // ✅ Create instance of RegisterControl
                    LoginControl clientCallback = new LoginControl();

                    ClientRMI.clientRemote.registerClient(fullName, (ClientInterface) clientCallback);

                    onlineCustomers.add(name);

                    // ✅ Login success and event found
                    JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    SwingUtilities.invokeLater(() -> new ProfilePage(cusRes, eventRes));
                } else {
                    // ⚠️ Login ok but event not found
                    JOptionPane.showMessageDialog(null, "Event not found for this customer.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                // ❌ Login failed
                JOptionPane.showMessageDialog(null, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Server error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (UserExistsException | AlreadyLoggedInException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Login Error", JOptionPane.WARNING_MESSAGE);
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
