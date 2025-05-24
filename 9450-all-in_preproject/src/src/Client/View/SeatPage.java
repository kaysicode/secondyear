package Client.View;

import Client.Controller.ClientController;
import Client.Model.ClientModel;
import Client.Model.Customer;
import Client.Model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeatPage extends JFrame {

        // Components of West Panel Side
        JPanel westPanel;
        JLabel text;
        JLabel imageIcon;
        JLabel textTeam;
        JLabel titleText;
        JLabel giantSteps;
        JLabel eventName;
        JLabel time;
        JLabel location;
        JLabel availSeats;
        JLabel description;

        // Components of East Panel Side
        JPanel eastPanel;
        JLabel legends;
        JLabel takenColor;
        JLabel taken;
        JLabel availableColor;
        JLabel available;
        JLabel unavailableColor;
        JLabel unAvailable;
        JLabel stage;

        // Buttons
        JButton button, backButton;

        private Timer refreshTimer;

        public SeatPage(Event event) {
                // Frame
                ImageIcon imageApp = new ImageIcon("src/src/Res/App Logo (ICON).png");
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLayout(null);
                this.setSize(1491, 791);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                this.setIconImage(imageApp.getImage());
                this.setTitle("Seat Content Page");

                // West Panel
                westPanel = new JPanel();
                westPanel.setBounds(0, 0, 671, 791);
                westPanel.setLayout(null);
                westPanel.setBackground(Color.WHITE);
                this.add(westPanel);

                // Text Logo (West Panel)
                text = new JLabel("<html><span style='font-family: DM Sans; " +
                        "font-weight: bold; " +
                        "font-size: 27px; "
                        + "letter-spacing: 0.5px; " +
                        "color: #B0CF8B;'>FLOWEVENT</span></html>");
                text.setBounds(214, 67, 350, 53);
                westPanel.add(text);

                // Image Logo (West Panel)
                imageIcon = new JLabel();
                ImageIcon image = new ImageIcon("src/src/Res/App Logo (WO BG).png");
                Image newImage = image.getImage().getScaledInstance(168, 168, Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newImage);
                imageIcon.setIcon(img);
                imageIcon.setBounds(54, 19, 168, 168);
                westPanel.add(imageIcon);

                // Line Separator
                JPanel coloredLine = new JPanel();
                coloredLine.setBounds(661, 36, 3, 700);
                coloredLine.setBackground(new Color(0xB0CF8B));
                westPanel.add(coloredLine);

                // TEAM ALL IN text (West Panel)
                textTeam = new JLabel("<html><span style='font-family: DM Sans; " +
                        "font-weight: 500; " +
                        "font-size: 12px; " +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'>Team ALL IN</span></html>");
                textTeam.setBounds(75, 181, 120, 20);
                westPanel.add(textTeam);

                // Title Text (West Panel)
                titleText = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: bold; " +
                        "font-size: 45px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> Choose a seat</span></html>");
                titleText.setBounds(75, 203, 450, 75);
                westPanel.add(titleText);

                // Giant Steps Image (West Panel)
                giantSteps = new JLabel();
                ImageIcon image2 = new ImageIcon("src/src/Res/GiantSteps.png");
                Image newImage2 = image2.getImage().getScaledInstance(600, 350, Image.SCALE_SMOOTH);
                ImageIcon img2 = new ImageIcon(newImage2);
                giantSteps.setIcon(img2);
                giantSteps.setBounds(15, 230, 600, 350);
                westPanel.add(giantSteps);

                // Event Name (West Panel)
                eventName = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: bold; " +
                        "font-size: 24px;" +
                        "letter-spacing: 0.5px; " +
                        "color: #000000;'> " + event.getEventName() + "</span></html>");
                eventName.setBounds(58, 520, 334, 69);
                westPanel.add(eventName);

                // Event Time (West Panel)
                time = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: regular; " +
                        "font-size: 10px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> " + event.getEvMonth() + " " + event.getEvDay() + ", "
                        + event.getStartTime() + " - " + event.getEndTime() + "</span></html>");
                time.setBounds(58, 575, 433, 22);
                westPanel.add(time);

                // Event Location (West Panel)
                location = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: regular; " +
                        "font-size: 9px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> Giant Steps at Saint Louis University, Maryheights Campus </span></html>");
                location.setBounds(58, 595, 590, 22);
                westPanel.add(location);

                // Event Available Seats (West Panel)
                availSeats = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: regular; " +
                        "font-size: 9px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> Available Seats Left: 7</span></html>");
                availSeats.setBounds(58, 615, 220, 22);
                westPanel.add(availSeats);

                description = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: regular; " +
                        "font-size: 9px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'><b>Event Description: </b>" + event.getDescription() + "</span></html>");
                description.setBounds(58, 630, 500, 60);
                westPanel.add(description);

                // -- END OF WEST PANEL --

                // East Panel
                eastPanel = new JPanel();
                eastPanel.setBounds(671, 0, 820, 791);
                eastPanel.setLayout(null);
                eastPanel.setBackground(Color.WHITE);
                this.add(eastPanel);

                // LEGENDS Text (East Panel)
                legends = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: bold; " +
                        "font-size: 14px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> LEGENDS: </span></html>");
                legends.setBounds(32, 44, 106, 22);
                legends.setHorizontalAlignment(SwingConstants.LEFT);
                eastPanel.add(legends);

                // Taken Text & Box (East Panel)
                takenColor = new JLabel();
                takenColor.setBackground(Color.RED);
                takenColor.setOpaque(true);
                takenColor.setBounds(168, 44, 26, 21);
                eastPanel.add(takenColor);
                taken = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: regular; " +
                        "font-size: 14px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> Taken </span></html>");
                taken.setBounds(197, 44, 56, 22);
                eastPanel.add(taken);

                // Available Text & Box (East Panel)
                availableColor = new JLabel();
                availableColor.setBackground(Color.LIGHT_GRAY);
                availableColor.setOpaque(true);
                availableColor.setBounds(271, 44, 26, 21);
                eastPanel.add(availableColor);
                available = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: regular; " +
                        "font-size: 14px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> Available </span></html>");
                available.setBounds(305, 44, 86, 22);
                eastPanel.add(available);

                // Unavailable Text & Box (East Panel)
                unavailableColor = new JLabel();
                unavailableColor.setBackground(Color.BLACK);
                unavailableColor.setOpaque(true);
                unavailableColor.setBounds(405, 44, 26, 21);
                eastPanel.add(unavailableColor);
                unAvailable = new JLabel("<html><span style='font-family: DM Sans;" +
                        "font-weight: regular; " +
                        "font-size: 14px;" +
                        "letter-spacing: 0.15px; " +
                        "color: #000000;'> Unavailable </span></html>");
                unAvailable.setBounds(439, 44, 100, 22);
                eastPanel.add(unAvailable);

                // Seat Buttons (LEFT SIDE)
                int x = 121; // initial x pos
                int y = 137; // initial y pos
                int bw = 73; // width
                int bh = 58; // height
                int horizontalSpacing = 15;
                int verticalSpacing = 79;
                int[] buttonNumbers = {36, 35, 34, 30, 29, 28, 24, 23, 22, 18, 17, 16, 12, 11, 10, 6, 5, 4};
                for (int i = 0; i < buttonNumbers.length; i++) {
                        button = new JButton();
                        button.setText(String.valueOf(buttonNumbers[i]));
                        button.setFont(new Font("DM Sans", Font.BOLD, 27));
                        button.setOpaque(true);

                        button.addActionListener(e -> {
                                JButton clickedButton = (JButton) e.getSource();
                                int clickedSeat = Integer.parseInt(clickedButton.getText());
                                if (clickedButton.getBackground().equals(Color.RED)) {
                                        takenSeatOptionPane();
                                } else {
                                        ClientController.seatButtons("GRAY", this, clickedSeat, event);
                                }
                                updateSeatAvailability(event);
                        });

                        button.setFocusable(false);
                        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        int row = i / 3; // Each Row has 3 buttons
                        int column = i % 3;
                        button.setBounds(x + column * (bw + horizontalSpacing),
                                y + row * verticalSpacing, bw, bh);
                        eastPanel.add(button);
                }

                // Seat Buttons (RIGHT SIDE)
                int x1 = 447;
                int[] buttonNumbers2 = {33, 32, 31, 27, 26, 25, 21, 20, 19, 15, 14, 13, 9, 8, 7, 3, 2, 1};

                for (int j = 0; j < buttonNumbers2.length; j++) {
                        button = new JButton();
                        button.setText(String.valueOf(buttonNumbers2[j]));
                        button.setFont(new Font("DM Sans", Font.BOLD, 27));
                        button.setOpaque(true);

                        button.addActionListener(e -> {
                                JButton clickedButton = (JButton) e.getSource();
                                int clickedSeat = Integer.parseInt(clickedButton.getText());
                                if (clickedButton.getBackground().equals(Color.RED)) {
                                        takenSeatOptionPane();
                                } else {
                                        ClientController.seatButtons("GRAY", this, clickedSeat, event);
                                }
                                updateSeatAvailability(event);
                        });

                        button.setFocusable(false);
                        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        int row = j / 3; // Each Row has 3 buttons
                        int column = j % 3;
                        button.setBounds(x1 + column * (bw + horizontalSpacing),
                                y + row * verticalSpacing, bw, bh);
                        eastPanel.add(button);
                }

                stage = new JLabel();
                stage.setBounds(258, 621, 292, 89);
                stage.setBackground(Color.GRAY);
                stage.setOpaque(true);
                stage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                eastPanel.add(stage);

                // Back Button
                backButton = new JButton("Back");
                backButton.setBounds(32, 700, 100, 30);
                backButton.setFont(new Font("DM Sans", Font.PLAIN, 14));
                backButton.setBackground(new Color(176, 207, 139));
                backButton.setForeground(Color.WHITE);
                backButton.setFocusable(false);
                backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                dispose(); // Close the current frame
                                new EventPage(); // Open the EventPage frame
                        }
                });
                eastPanel.add(backButton);

                // Timer for real-time updates (e.g., every 2 seconds)
                refreshTimer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                updateSeatAvailability(event);  // Update the seat availability every 2 seconds
                        }
                });
                refreshTimer.start();  // Start the timer

                this.setVisible(true);
        }

        private Set<Integer> getUpdatedTakenSeats(Event event) {
                Set<Integer> updatedTakenSeats = new HashSet<>();
                List<Customer> customerList = (ArrayList<Customer>) ClientModel.getCustomerList();
                String eventID = event.getEventID();  // Get the current event's ID

                for (Customer customer : customerList) {
                        if (customer.getEventID().equals(eventID)) {  // If event ID matches
                                updatedTakenSeats.add(customer.getSeatNo());  // Add customer's booked seat
                        }
                }
                return updatedTakenSeats;
        }

        private void updateSeatAvailability(Event event) {
                Set<Integer> updatedTakenSeats = getUpdatedTakenSeats(event);
                for (Component component : eastPanel.getComponents()) {
                        if (component instanceof JButton) {
                                JButton seatButton = (JButton) component;
                                int seatNumber = Integer.parseInt(String.valueOf(seatButton.getText()));

                                if (updatedTakenSeats.contains(seatNumber)) {
                                        seatButton.setBackground(Color.RED);  // Seat is taken
                                } else {
                                        seatButton.setBackground(Color.LIGHT_GRAY);  // Seat is available
                                }

                                seatButton.repaint();  // Ensure button UI is refreshed
                                seatButton.revalidate();  // Revalidate the button layout
                        }
                }
        }

        @Override
        public void dispose() {
                if (refreshTimer != null) {
                        refreshTimer.stop();  // Stop the timer when closing the frame
                }
                super.dispose();
        }

        public static void takenSeatOptionPane() {
                JOptionPane.showMessageDialog(null, "This Seat is already Taken", "Taken Seat",
                        JOptionPane.ERROR_MESSAGE);
        }
}