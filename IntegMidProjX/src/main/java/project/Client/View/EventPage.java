package project.Client.View;

import project.Client.Controller.EventControl;
import project.Client.Controller.WelcomeControl;
import project.Client.Model.Links;
import project.Client.Model.Months;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class EventPage extends JFrame {
    private JFrame frame;
    private JPanel mainPanel, eventsPanel;
    private JLabel imageIcon, flowEventText, textTeam, eventText;
    private JComboBox<String> eventDropdown;
    private JScrollPane scrollEvent;

    public EventPage() {
        // Frame
        Image iconImage = new ImageIcon(Links.APP_LOGO_ICON.getFilePath()).getImage();
        frame = new JFrame("Event Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1491, 791);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(iconImage);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1491, 791);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // Event Panel
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new FlowLayout());
        eventsPanel.setBackground(Color.WHITE);

        // Scroll Pane
        scrollEvent = new JScrollPane(eventsPanel);
        scrollEvent.setBounds(75, 300, 1300, 400);

        // APP Logo Image
        imageIcon = new JLabel();
        ImageIcon image = new ImageIcon(Links.APP_LOGO_WOBG.getFilePath());
        Image newImage = image.getImage().getScaledInstance(168, 168, Image.SCALE_SMOOTH);
        imageIcon.setIcon(new ImageIcon(newImage));
        imageIcon.setBounds(54, 19, 168, 168);

        // Group's APP Logo Name Label ("FLOWEVENT")
        flowEventText = new JLabel("<html><span style='font-family: DM Sans; font-weight: bold; font-size: 27px; color: #B0CF8B;'>FLOWEVENT</span></html>");
        flowEventText.setBounds(214, 67, 350, 53);

        // Group Name Label ("ALL IN")
        textTeam = new JLabel("<html><span style='font-family: DM Sans; font-weight: 500; font-size: 12px; color: #000000;'>Team ALL IN</span></html>");
        textTeam.setBounds(75, 211, 120, 20);

        // Event Text Label ("Events")
        eventText = new JLabel("<html><b><span style='font-family: DM Sans; font-weight: 500; font-size: 35px; color: #000000;'>Events:</span></b></html>");
        eventText.setBounds(75, 221, 400, 80);

        eventDropdown = new JComboBox<>(EventControl.populate());
        eventDropdown.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        eventDropdown.setBackground(Color.WHITE);
        eventDropdown.setBounds(1130, 210, 270, 30);
        eventDropdown.addActionListener(e -> {
            try {
                EventControl.filter(eventDropdown, eventsPanel, frame);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });

        // add to the mainPanel
        mainPanel.add(scrollEvent);
        mainPanel.add(eventDropdown);
        mainPanel.add(textTeam);
        mainPanel.add(flowEventText);
        mainPanel.add(imageIcon);
        mainPanel.add(eventText);

        frame.add(mainPanel);
        frame.setVisible(true);

        try {
            EventControl.loadAllEvents();
            EventControl.displayEvents(frame, eventsPanel);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
