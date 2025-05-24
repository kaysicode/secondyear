package project.Client.View;

import javax.swing.*;
import java.awt.*;

import project.Client.Controller.SeatControl;
import project.Client.Controller.WelcomeControl;
import project.Client.Model.Event;
import project.Client.Model.Links;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

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
    JLabel description;

    // Components of East Panel Side
    JPanel eastPanel;
    JLabel legends;
    JLabel takenColor;
    JLabel taken;
    JLabel availableColor;
    JLabel available;
    JLabel stage;

    // Buttons
    JButton backButton;

    private static Integer selectedSeat = null;


    public SeatPage(Event e) {
        // Frame
        ImageIcon imageApp = new ImageIcon(Links.APP_LOGO_ICON.getFilePath());
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
        ImageIcon image = new ImageIcon(Links.APP_LOGO_WOBG.getFilePath());
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
        ImageIcon image2 = new ImageIcon(Links.GIANT_STEPS.getFilePath());
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
                "color: #000000;'> " + e.eventName() + "</span></html>");
        eventName.setBounds(58, 520, 334, 69);
        westPanel.add(eventName);

        // Event Time (West Panel)
        time = new JLabel("<html><span style='font-family: DM Sans;" +
                "font-weight: regular; " +
                "font-size: 10px;" +
                "letter-spacing: 0.15px; " +
                "color: #000000;'> " + e.eventMonth() + " " + e.eventDay() + ", "
                + e.startTime() + " - " + e.endTime() + "</span></html>");
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

        // Event Description(West Panel)
        description = new JLabel("<html><span style='font-family: DM Sans;" +
                "font-weight: regular; " +
                "font-size: 9px;" +
                "letter-spacing: 0.15px; " +
                "color: #000000;'><b>Event Description: </b>" + e.description() + "</span></html>");
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

        // Seat Buttons (LEFT SIDE)
        addLeftSeatButtons(eastPanel, e);

        // Seat Buttons (RIGHT SIDE)
        addRightSeatButtons(eastPanel, e);

        SeatControl.updateSeatAvailability(e, eastPanel);

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
        backButton.addActionListener(e1 -> {
            try {
                WelcomeControl.getActionButton("GO_TO_EVENT_PAGE", this);
            } catch (RemoteException y) {
                throw new RuntimeException(y);
            }
        });
        eastPanel.add(backButton);


        this.setVisible(true);
    }

    // Seat Buttons (LEFT SIDE)
    private void addLeftSeatButtons(JPanel panel, Event event) {
        int x = 121;
        int y = 137;
        int bw = 73;
        int bh = 58;
        int horizontalSpacing = 15;
        int verticalSpacing = 79;
        int[] buttonNumbers = {36, 35, 34, 30, 29, 28, 24, 23, 22, 18, 17, 16, 12, 11, 10, 6, 5, 4};

        for (int i = 0; i < buttonNumbers.length; i++) {
            JButton button = new JButton(String.valueOf(buttonNumbers[i]));
            button.setFont(new Font("DM Sans", Font.BOLD, 27));
            button.setOpaque(true);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                int seatNumber = Integer.parseInt(clickedButton.getText());
                SeatControl.registerSeatButton(seatNumber, button);
                SeatControl.seatValidation(seatNumber, button, this, e, event, this);

            });

            int row = i / 3;
            int column = i % 3;
            button.setBounds(x + column * (bw + horizontalSpacing),
                    y + row * verticalSpacing, bw, bh);
            panel.add(button);
        }
    }

    // Seat Buttons (RIGHT SIDE)
    private void addRightSeatButtons(JPanel panel, Event event) {
        int x = 447;
        int y = 137;
        int bw = 73;
        int bh = 58;
        int horizontalSpacing = 15;
        int verticalSpacing = 79;
        int[] buttonNumbers2 = {33, 32, 31, 27, 26, 25, 21, 20, 19, 15, 14, 13, 9, 8, 7, 3, 2, 1};

        for (int j = 0; j < buttonNumbers2.length; j++) {
            JButton button = new JButton(String.valueOf(buttonNumbers2[j]));
            button.setFont(new Font("DM Sans", Font.BOLD, 27));
            button.setOpaque(true);
            button.setFocusable(false);
            button.setBackground(Color.LIGHT_GRAY);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                int seatNumber = Integer.parseInt(clickedButton.getText());
                SeatControl.registerSeatButton(seatNumber, button);
                SeatControl.seatValidation(seatNumber, button, this, e, event, this);
            });
            int row = j / 3;
            int column = j % 3;
            button.setBounds(x + column * (bw + horizontalSpacing),
                    y + row * verticalSpacing, bw, bh);
            panel.add(button);
        }
    }

    public void setSelectedSeat(int seat) {
        selectedSeat = seat;
    }

    public static Integer getSelectedSeat() {
        return selectedSeat;
    }



}