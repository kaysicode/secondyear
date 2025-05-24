package project.Client.Controller;

import project.Client.ClientRMI;
import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Client.Model.Links;
import project.Client.View.WelcomePage;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;

public class ProfileControl {

    // Create the list model and populate it with online customers
    public static DefaultListModel<String> listModel = new DefaultListModel<>();

    public static String checkGender(Customer cus) {
        if (cus.gender().equalsIgnoreCase("Male")) {
            return Links.MALE_PROFILE.getFilePath();
        }
        return Links.FEMALE_PROFILE.getFilePath();
    }

    public static void createNewOne(JFrame frame, Customer cus) {
        String[] arr1 = {"Continue", "Nuh uh"};
        int answer = JOptionPane.showOptionDialog(
                null,
                "Your account will be deleted! Still want to continue?",
                "Verification",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                arr1,
                0);

        if (answer == 1) {
            System.out.println("You entered NO");
        } else if (answer == 0) {
            System.out.println("You entered YES");

            try {
                // Read all customers
                List<Customer> customerList = ClientRMI.jsonRemote.readToJSON(
                        Links.CUSTOMER_JSON.getFilePath(),
                        Customer.class
                );

                // Remove matching customer
                boolean removed = customerList.removeIf(c ->
                        c.email().equals(cus.email()) && c.password().equals(cus.password())
                );

                if (removed) {
                    // Save updated list
                    ClientRMI.jsonRemote.updateToJSON(
                            Links.CUSTOMER_JSON.getFilePath(),
                            customerList
                    );

                    JOptionPane.showMessageDialog(null, "Account deleted successfully.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    logCustomer("CREATE_NEW_ONE", frame, cus);
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (RemoteException e) {
                JOptionPane.showMessageDialog(null, "Error deleting account: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            System.out.println("Action canceled.");
        }
    }

    public static void showOnlineCustomers(List<String> onlineCustomers) {
        JFrame onlineFrame = new JFrame("Online Customers");
        onlineFrame.setSize(400, 300);
        onlineFrame.setLocationRelativeTo(null);
        onlineFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Clear the list before adding new entries
        listModel.clear();

        // Create a panel to hold the label and the list
        JPanel mainPanel = new JPanel(new BorderLayout());
        onlineFrame.add(mainPanel);

        JLabel titleLabel = new JLabel("ONLINE CUSTOMERS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        for (String customer : onlineCustomers) {
            listModel.addElement(customer);
        }

        JList<String> customerList = new JList<>(listModel);
        customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerList.setLayoutOrientation(JList.VERTICAL);
        customerList.setVisibleRowCount(-1);
        customerList.setFont(new Font("Candara", Font.PLAIN, 18));

        JScrollPane listScroller = new JScrollPane(customerList);
        mainPanel.add(listScroller, BorderLayout.CENTER);

        onlineFrame.setVisible(true);
    }

    public static void logCustomer(String action, JFrame frame, Customer cus) {
        try {
            // Call the logout on the server using the customer's email
            String name = cus.firstName() + " " + cus.lastName();
            ClientRMI.clientRemote.logoutCall(name);

            if ("EXIT_PROGRAM".equals(action)) {
                // Close the current frame
                frame.dispose();
                System.exit(0);
            } else if ("CREATE_NEW_ONE".equals(action)) {
                frame.dispose();
                SwingUtilities.invokeLater(WelcomePage::new);
            }

        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Failed to logout: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Unexpected error during logout.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }





}
