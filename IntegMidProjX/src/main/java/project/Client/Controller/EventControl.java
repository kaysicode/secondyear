package project.Client.Controller;

import project.Client.ClientRMI;
import project.Client.Model.Event;
import project.Client.Model.Links;
import project.Client.Model.Months;
import project.Client.View.SeatPage;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventControl {

    public static List<Event> originalEventList = new ArrayList<>(); // full JSON loaded once
    public static List<Event> eventList = new ArrayList<>();

    public static void loadAllEvents() throws RemoteException {
        originalEventList = ClientRMI.jsonRemote.readToJSON(
                Links.EVENT_JSON.getFilePath(),
                Event.class);
        eventList = new ArrayList<>(originalEventList); // initialize working copy
    }

    public static void displayEvents(JFrame frame, JPanel panel) throws RemoteException {
        panel.removeAll();
        Random r = new Random();

        for (Event e : eventList) {
            JButton eventBtn = new JButton();
            Color buttonColor;

            if (e.eventName().equalsIgnoreCase("Valentines Day")) {
                buttonColor = Color.PINK;
            } else if (e.eventName().equalsIgnoreCase("ICON: Awardees")) {
                buttonColor = Color.ORANGE;
            } else {
                float hue = r.nextFloat();
                float saturation = (0.9f + r.nextFloat()) / 2.0f;
                float brightness = (0.9f + r.nextFloat()) / 2.0f;
                buttonColor = Color.getHSBColor(hue, saturation, brightness);
            }

            /*
             * TODO: Add the event year
             */
            eventBtn.setBackground(buttonColor);
            eventBtn.setOpaque(true);
            eventBtn.setPreferredSize(new Dimension(380, 180));
            eventBtn.setText("<html><div style='padding-left: 15px;'>"
                    + "<table style='text-align: left;'>"
                    + "<tr><td style='font-size:16px; color:white;'>" + e.eventName() + "</td></tr>"
                    + "<tr><td style='font-size:18px; font-weight:bold; color:black;'>" + e.eventMonth() + " " + e.eventDay() + "</td></tr>"
                    + "<tr><td style='font-size:10px; font-weight:regular; color:white;'>" + e.startTime() + " - " + e.endTime() + "</td></tr>"
                    + "<tr><td style='font-size:7px; font-weight:regular; color:white;'>" + e.description() + "</td></tr>"
                    + "</table>"
                    + "</div></html>");

            eventBtn.setFocusPainted(false);
            eventBtn.setBorderPainted(false);
            eventBtn.setHorizontalAlignment(SwingConstants.LEFT);

            // Call the method in the Controller (WelcomeControl.java)
            eventBtn.addActionListener(e1 -> {
                try {
                    // Check if valid date, only proceed if it's true
                    if (validate(frame, e.eventMonth(), e.eventDay())) {
                        eventSeat("GO_TO_SEAT_PAGE", frame, e);
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            });
            panel.add(eventBtn);
        }

        panel.revalidate();
        panel.repaint();

    }

    public static void eventSeat (String action, JFrame frame, Event e) throws RemoteException, RuntimeException {
        // Fetch the response to know what action will the getActionButton() method do
        String response = ClientRMI.eventRemote.buttonAction(action);

        if ("GO_TO_SEAT_PAGE".equals(response)) {
            frame.dispose();
            SwingUtilities.invokeLater(() -> new SeatPage(e));
        }
    }

    public static boolean validate(JFrame frame, String month, int day) {
        // Validate date selection
        boolean isResponse;
        try {
            isResponse = ClientRMI.eventRemote.isValidDate(month, day);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        if (!isResponse) {
            JOptionPane.showMessageDialog(frame,
                    "The selected Event is already done!",
                    "Event Done",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static String[] populate() {
        Months[] months = Months.values();
        String[] formattedMonths = new String[months.length];

        for (int i = 0; i < months.length; i++) {
            formattedMonths[i] = formatEnumName(months[i].name());
        }

        return formattedMonths;
    }

    private static String formatEnumName(String enumName) {
        String formatted = enumName.substring(0, 1).toUpperCase() + enumName.substring(1).toLowerCase();
        return formatted + " Events";
    }

    public static void filter(JComboBox<String> dropdown, JPanel panel, JFrame frame) throws RemoteException {
        String selectedFilter = (String) dropdown.getSelectedItem();
        if (selectedFilter == null) return;

        String rawMonth = selectedFilter.replace(" Events", "").toUpperCase();

        eventList = ClientRMI.eventRemote.filterEvents(rawMonth, originalEventList); // calls the remote method
        displayEvents(frame, panel); // redraw with filtered list
    }


}
