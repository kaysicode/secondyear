package project.Server.Servant;

import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Client.Model.Months;
import project.Client.View.EventPage;
import project.Client.View.WelcomePage;
import project.Interfaces.EventInterface;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class EventServant extends UnicastRemoteObject implements EventInterface {

    public EventServant () throws RemoteException {}

    @Override
    public String buttonAction(String action) throws RemoteException {
        return switch (action) {
            case "GO_TO_EVENT_PAGE" -> "GO_TO_EVENT_PAGE";
            case "EXIT_PROGRAM" -> "EXIT_PROGRAM";
            case "GO_TO_SEAT_PAGE" -> "GO_TO_SEAT_PAGE";
            case "BACK_EVENT_PAGE" -> "BACK_EVENT_PAGE";
            case "BACK_SEAT_PAGE" -> "BACK_SEAT_PAGE";
            case "GO_TO_PROFILE" -> "GO_TO_PROFILE";
            case "GO_TO_WELCOME" -> "GO_TO_WELCOME";
            default -> "DENIED";
        };
    }

    @Override
    public boolean isValidDate(String month, int day) throws RemoteException {
        Calendar current = Calendar.getInstance();
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(Months.getIndexByName(month), day);

        return eventDate.after(current) || eventDate.equals(current);
    }

    @Override
    public List<Event> filterEvents(String rawMonth, List<Event> data) throws RemoteException {
        if (rawMonth.equalsIgnoreCase("ALL_EVENTS")) {
            return data;
        }
        List<Event> filteredList = new ArrayList<>();
        for (Event event : data) {
            if (event.eventMonth().equalsIgnoreCase(rawMonth)) {
                filteredList.add(event);
            }
        }
        return filteredList;
    }

    @Override
    public List<Integer> getUpdatedTakenSeats(List<Customer> data, Event e) throws RemoteException {
        List<Integer> seatNo = new ArrayList<>();

        if (data == null || e == null || e.eventID() == null) {
            System.out.println("Received null data or event");
            return seatNo;
        }

        String eID = e.eventID();

        for (Customer customer : data) {
            if (customer == null || customer.eventID() == null) continue;

            if (customer.eventID().equals(eID)) {
                seatNo.add(customer.seatNo());
            }
        }

        return seatNo;
    }

    @Override
    public boolean registerTxtBoxSubmitted(String fName, String lName, String contNo, String email, JFrame frame, Event event) throws RemoteException {

        // First Name and Last Name must be letters only
        if (!fName.matches("[a-zA-Z]+")) return false;
        if (!lName.matches("[a-zA-Z]+")) return false;

        // Contact number must be 11 digits
        if (!contNo.matches("\\d{11}")) return false;

        // Email must follow proper format
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) return false;

        return true; // All good
    }

    @Override
    public boolean passwordConfirmation(String firstPass, String lastPass) throws RemoteException {
        return firstPass.equals(lastPass);
    }

    @Override
    public String generateEventID(Customer cus) throws RemoteException {
        return cus.eventID()+"S"+cus.seatNo();

    }

}
