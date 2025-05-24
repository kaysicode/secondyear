package project.Interfaces;

import project.Client.Model.Customer;
import project.Client.Model.Event;
import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface EventInterface extends Remote {

    // All of buttons
    /*
    TODO: This interface method will be used by buttons that trigger navigation between pages.
       - For example, navigating from WelcomePage to EventPage.
       - Pass a String action like "GoToEventPage", "BackToWelcome", etc.
       - Also pass the current JFrame so it can be disposed.
       - Use if-else or switch-case to check the action and perform the corresponding page change.
    */
    public String buttonAction(String action) throws RemoteException;

    // EventPage
    /*
    TODO: Validates whether the given year, month, and day represent a valid date.
       - Useful for checking if an event date is correctly selected by the user.
       - Return true if valid, false otherwise.
    */
    public boolean isValidDate(String month, int day) throws RemoteException;

    /*
     TODO: Applies a filter to events based on user input in a JComboBox.
           - For example, filter by month or event type.
           - Update the EventPage to show only the matching events.
     */
    public List<Event> filterEvents(String rawMonth, List<Event> data) throws RemoteException;

    // SeatPage
    /*
     TODO: Returns a set of seat numbers that are already taken for the selected event.
           - This data can be used to mark unavailable seats in the GUI.
           - Typically updated in real-time when multiple clients are connected.
     */
    public List<Integer> getUpdatedTakenSeats(List<Customer> data, Event e) throws RemoteException;

    // RegisterPage
    /*
    TODO: Called when user submits their registration form (first name, last name, contact number, email).
       - Includes the current JFrame and selected Event.
       - Server should validate and store the data (e.g., write to customer.json).
       - Can also trigger moving to a confirmation or password page.
    */
    public boolean registerTxtBoxSubmitted(String fName, String lName, String contNo, String email, JFrame frame, Event event) throws RemoteException;

    public boolean passwordConfirmation(String firstPass, String lastPass) throws RemoteException;

    /*
     TODO: Generates a unique EventID string for each registration.
           - Example: "E01A", "E02B", etc.
           - Should ensure uniqueness across different registrations.
     */
    public String generateEventID(Customer cus) throws RemoteException;

    // PasswordPage
    /*
    TODO: Confirms if the two entered passwords match (e.g., user entered same password twice).
       - Used for password confirmation during sign-up or event registration.
       - You can return a success message or trigger a GUI change depending on the result.
    */



}
