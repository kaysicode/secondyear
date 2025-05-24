package Client.Controller;

import Client.Model.Event;
import Client.View.*;
import Client.Model.Customer;
import Client.Model.ClientModel;


import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The ClientController class manages the interaction between the view and the model.
 * It handles user actions and updates the view accordingly.
 */
public class ClientController {

    private static int seatTaken;
    private static List<Customer> customerList = new ArrayList<>();

    public ClientController(String seatTaken) {
        this.seatTaken = Integer.parseInt(seatTaken);
    }

    public static void exitButton( JFrame frame) {
        frame.dispose();
        new WelcomePage();
        frame.dispose();

    }

    // dispose current frame and launch EventPage
    public static void registerButton(String action, JFrame frame) {
        if (action.equals("register")) {
            if (frame != null) {
                frame.dispose(); 
            }
            new EventPage(); 
        } else if (action.equals("exit")) {
            System.exit(0); 
        }
    }

    // dispose current frame and open SeatPage 
    public static void eventButton(String action, JFrame frame, Event event) {
        if (action.equals("thisButton")) {
            if (frame != null) {
                frame.dispose();
            }
            new SeatPage(event);
        }
    }
    

    // check if seat is taken 
    public static void seatButtons(String action, JFrame frame, int seatTaken, Event event) {
        ClientController.seatTaken = seatTaken;
        if (action.equals("RED")) {
            SeatPage.takenSeatOptionPane();
        } else if (action.equals("GRAY")){
            frame.dispose();
            new RegisterPage(event);
        } else {
            SeatPage.takenSeatOptionPane();
        }
    }

    // check input names 
    public static void registerTxtBoxSubmitted(String fName, String lName, String contNo, String email, JFrame frame, Event event) {
        if (!fName.matches("[a-zA-Z]+")) {
            RegisterPage.showJOptionPane("fName");
            return;
        }
        if (!lName.matches("[a-zA-Z]+")) {
            RegisterPage.showJOptionPane("lName");
            return;
        }

        // check contact number length
        if (!contNo.matches("\\d{11}")) {
            if (!contNo.matches("\\d+")) {
                RegisterPage.showJOptionPane("Error 1"); // Not numeric
                return;
            } else {
                RegisterPage.showJOptionPane("Error 2"); // Wrong length
                return;
            }
        }

        // check email if valid
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            RegisterPage.showJOptionPane("email");
            return;
        }

        String EventID = event.getEventID();

        // create the Customer object
        String[] customerData = {
                EventID,
                lName,
                fName,
                contNo,
                email,
        };

        Customer customer = new Customer(customerData, String.valueOf(seatTaken));
        customerList = new ArrayList<>();
        customerList.add(customer);

        // send the customer list to the server
        try {
            ObjectOutputStream outputStream = ClientModel.getWrite();
            outputStream.writeObject(customerList);
            outputStream.flush();
            System.out.println("Customer data sent to server.");
        } catch (IOException e) {
            System.out.println("❌ Error sending customer data to server: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
        new ThankYouPage();


    }

    public static String generateEventID() {
        String cusID = "";
        for(Customer cus : customerList) {
            cusID = cus.getEventID()+"S"+cus.getSeatNo();
        }
        return cusID;
    }
}