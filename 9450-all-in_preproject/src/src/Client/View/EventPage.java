package Client.View;

import Client.Controller.ClientController;
import Client.Model.ClientModel;
import Client.Model.Event;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventPage extends JFrame {
    private JFrame frame;
    private JPanel mainPanel, eventsPanel;
    private JLabel imageIcon, flowEventText, textTeam, eventText;
    private JComboBox<String> eventDropdown;
    private JScrollPane scrollEvent;
    private List<Event> eventList = new ArrayList<>(); // Store all events for filtering

    public EventPage() {
        frame = new JFrame("Event Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1491, 791);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1491, 791);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        eventsPanel = new JPanel();
        eventsPanel.setLayout(new FlowLayout());
        eventsPanel.setBackground(Color.WHITE);

        scrollEvent = new JScrollPane(eventsPanel);
        scrollEvent.setBounds(75, 300, 1300, 400);

        imageIcon = new JLabel();
        ImageIcon image = new ImageIcon("src/src/Res/App Logo (WO BG).png");
        Image newImage = image.getImage().getScaledInstance(168, 168, Image.SCALE_SMOOTH);
        imageIcon.setIcon(new ImageIcon(newImage));
        imageIcon.setBounds(54, 19, 168, 168);

        flowEventText = new JLabel("<html><span style='font-family: DM Sans; font-weight: bold; font-size: 27px; color: #B0CF8B;'>FLOWEVENT</span></html>");
        flowEventText.setBounds(214, 67, 350, 53);

        textTeam = new JLabel("<html><span style='font-family: DM Sans; font-weight: 500; font-size: 12px; color: #000000;'>Team ALL IN</span></html>");
        textTeam.setBounds(75, 211, 120, 20);

        eventText = new JLabel("<html><b><span style='font-family: DM Sans; font-weight: 500; font-size: 35px; color: #000000;'>Events:</span></b></html>");
        eventText.setBounds(75, 221, 400, 80);

        // Dropdown for filtering events
        String[] eventStuff = {"All Events", "January Events",
                "February Events", "March Events", "April Events",
                "May Events", "June Events", "July Events", "August Events", "September Events",
                "October Events", "November Events", "December Events"};
        eventDropdown = new JComboBox<>(eventStuff);
        eventDropdown.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        eventDropdown.setBackground(Color.WHITE);
        eventDropdown.setBounds(1130, 210, 270, 30);

        // Add action listener to filter events
        eventDropdown.addActionListener(e -> filterEvents());

        mainPanel.add(scrollEvent);
        mainPanel.add(eventDropdown);
        mainPanel.add(textTeam);
        mainPanel.add(flowEventText);
        mainPanel.add(imageIcon);
        mainPanel.add(eventText);

        frame.add(mainPanel);
        frame.setVisible(true);

        loadEventsFromXML(); // Load events from XML
    }

    /**
     * Loads events from the XML and stores them in eventList.
     */
    private void loadEventsFromXML() {
        try {
            ObjectOutputStream outputStream = ClientModel.getWrite();
            outputStream.writeObject("REQUEST_EVENT");
            outputStream.flush();

            System.out.println("Request for the event submitted to the server");

            ObjectInputStream objectInputStream = ClientModel.getRead();
            eventList = (ArrayList<Event>) objectInputStream.readObject(); // Store events for filtering
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There's something wrong while fetching events.");
            e.printStackTrace();
        }

        displayEvents(eventList); // Initially display all events
    }

    /**
     * Filters events based on the selected month from the dropdown.
     */
    private void filterEvents() {
        String selectedFilter = (String) eventDropdown.getSelectedItem();
        List<Event> filteredList = new ArrayList<>();

        if (selectedFilter.equals("All Events")) {
            filteredList = eventList; // Show all events
        } else {
            String selectedMonth = selectedFilter.replace(" Events", ""); // Extract month name
            for (Event event : eventList) {
                if (event.getEvMonth().equalsIgnoreCase(selectedMonth)) {
                    filteredList.add(event);
                }
            }
        }

        displayEvents(filteredList);
    }

    /**
     * Displays the given list of events in the events panel.
     * @param events The list of events to display.
     */
    private void displayEvents(List<Event> events) {
        eventsPanel.removeAll(); // Clear current events
        Random random = new Random();

        for (Event event : events) {
            JButton eventBtn = new JButton();
            Color buttonColor;

            if (event.getEventName().equalsIgnoreCase("Valentines Day")) {
                buttonColor = Color.PINK;
            } else if (event.getEventName().equalsIgnoreCase("ICON: Awardees")) {
                buttonColor = Color.ORANGE;
            } else {
                float hue = random.nextFloat();
                float saturation = (0.9f + random.nextFloat()) / 2.0f;
                float brightness = (0.9f + random.nextFloat()) / 2.0f;
                buttonColor = Color.getHSBColor(hue, saturation, brightness);
            }

            eventBtn.setBackground(buttonColor);
            eventBtn.setOpaque(true);
            eventBtn.setPreferredSize(new Dimension(380, 180));
            eventBtn.setText("<html><div style='padding-left: 15px;'>"
                    + "<table style='text-align: left;'>"
                    + "<tr><td style='font-size:16px; color:white;'>" + event.getEventName() + "</td></tr>"
                    + "<tr><td style='font-size:18px; font-weight:bold; color:black;'>" + event.getEvMonth() + " " + event.getEvDay() + "</td></tr>"
                    + "<tr><td style='font-size:10px; font-weight:regular; color:white;'>" + event.getStartTime() + " - " + event.getEndTime() + "</td></tr>"
                    + "<tr><td style='font-size:7px; font-weight:regular; color:white;'>" + event.getDescription() + "</td></tr>"
                    + "</table>"
                    + "</div></html>");
            eventBtn.setFocusPainted(false);
            eventBtn.setBorderPainted(false);
            eventBtn.setHorizontalAlignment(SwingConstants.LEFT);
            eventBtn.addActionListener(e -> ClientController.eventButton("thisButton", frame, event));

            eventsPanel.add(eventBtn);
        }

        eventsPanel.revalidate();
        eventsPanel.repaint();
    }
}
