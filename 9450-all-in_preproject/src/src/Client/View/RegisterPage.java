package Client.View;

import Client.Controller.ClientController;
import Client.Model.Event;
import Server.XML.XMLReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.List;

public class RegisterPage extends JFrame {
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

    // Components of Right Panel Side
    JPanel rightPanel;
    JLabel firstNameLabel, firstNamePlaceholder;
    JTextField firstNameField;
    JLabel lastNameLabel, lastNamePlaceholder;
    JTextField lastNameField;
    JLabel contactNumberLabel, contactNumberPlaceholder;
    JTextField contactNumberField;
    JLabel emailLabel, emailPlaceholder;
    JTextField emailField;
    JButton submitButton;

    public RegisterPage(Event event) {

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
                "color: #000000;'>"+ event.getEventName()  + "</span></html>");
        eventName.setBounds(58, 520, 334, 69);
        westPanel.add(eventName);

        // Event Time (West Panel)
        time = new JLabel("<html><span style='font-family: DM Sans;" +
                "font-weight: regular; " +
                "font-size: 10px;" +
                "letter-spacing: 0.15px; " +
                "color: #000000;'> " + event.getEvMonth() + " " + event.getEvDay() + ", AT " + event.getStartTime() +"AM - " + event.getEndTime() +  "</span></html>");
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

        description = new JLabel("<html><span style='font-family: DM Sans;" +
                "font-weight: regular; " +
                "font-size: 9px;" +
                "letter-spacing: 0.15px; " +
                "color: #000000;'><b>Event Description: </b>" + event.getDescription() + "</span></html>");
        description.setBounds(58, 630, 500, 60);
        westPanel.add(description);

        // right panel
        rightPanel = new JPanel();
        rightPanel.setBounds(671, 0, 820, 791);
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.WHITE);
        this.add(rightPanel);

        // right Title
        JLabel formTitle = new JLabel("<html><span style='font-family: DM Sans;" +
                "font-weight: bold;" +
                " font-size: 21px;'>ENTER YOUR DETAILS TO BE ABLE TO REGISTER</span></html>");
        formTitle.setBounds(50, 50, 700, 30);
        rightPanel.add(formTitle);

        // First Name
        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        firstNameLabel.setBounds(150, 100, 200, 50);
        rightPanel.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(150, 140, 400, 40);
        firstNameField.setBackground(new Color(234, 234, 234));
        firstNameField.setForeground(new Color(64, 64, 64));
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        firstNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        rightPanel.add(firstNameField);
        firstNamePlaceholder = new JLabel("Enter your First Name (e.g. JUAN)");
        firstNamePlaceholder.setFont(new Font("Arial", Font.ITALIC, 10));
        firstNamePlaceholder.setForeground(Color.GRAY);
        firstNamePlaceholder.setBounds(150, 180, 400, 20);
        rightPanel.add(firstNamePlaceholder);

        // Last Name
        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        lastNameLabel.setBounds(150, 220, 150, 25);
        rightPanel.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(150, 260, 400, 40);
        lastNameField.setBackground(new Color(234, 234, 234));
        lastNameField.setForeground(new Color(64, 64, 64));
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        lastNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        rightPanel.add(lastNameField);
        lastNamePlaceholder = new JLabel("Enter your Last Name (e.g. DELA CRUZ)");
        lastNamePlaceholder.setFont(new Font("Arial", Font.ITALIC, 10));
        lastNamePlaceholder.setForeground(Color.GRAY);
        lastNamePlaceholder.setBounds(150, 300, 300, 20);
        rightPanel.add(lastNamePlaceholder);

        // Contact Number
        contactNumberLabel = new JLabel("Contact Number");
        contactNumberLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        contactNumberLabel.setBounds(150, 340, 150, 25);
        rightPanel.add(contactNumberLabel);
        contactNumberField = new JTextField();
        contactNumberField.setBounds(150, 380, 400, 40);
        contactNumberField.setBackground(new Color(234, 234, 234));
        contactNumberField.setForeground(new Color(64, 64, 64));
        contactNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        contactNumberField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        rightPanel.add(contactNumberField);
        contactNumberPlaceholder = new JLabel("Enter your Contact No. (e.g. 09123456789)");
        contactNumberPlaceholder.setFont(new Font("Arial", Font.ITALIC, 10));
        contactNumberPlaceholder.setForeground(Color.GRAY);
        contactNumberPlaceholder.setBounds(150, 420, 300, 20);
        rightPanel.add(contactNumberPlaceholder);

        // Email Address
        emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        emailLabel.setBounds(150, 460, 150, 25);
        rightPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(150, 500, 400, 40);
        emailField.setBackground(new Color(234, 234, 234));
        emailField.setForeground(new Color(64, 64, 64));
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        rightPanel.add(emailField);
        emailPlaceholder = new JLabel("Enter your Email Address (e.g. juandelacruz@gmail.com)");
        emailPlaceholder.setFont(new Font("Arial", Font.ITALIC, 10));
        emailPlaceholder.setForeground(Color.GRAY);
        emailPlaceholder.setBounds(150, 540, 400, 20);
        rightPanel.add(emailPlaceholder);

        // Submit Button
        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Roboto", Font.BOLD, 19));
        submitButton.setForeground(Color.BLACK);
        submitButton.setBackground(new Color(244, 17, 17, 255)); // Crimson Red
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(200, 610, 250, 50);
        submitButton.setFocusable(false);
        rightPanel.add(submitButton);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(new Color(184, 4, 49, 148)); // Darker Red on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(new Color(220, 20, 60)); // Reset Color
            }
        });

        // Action Listener for Validation
        submitButton.addActionListener(e -> ClientController.registerTxtBoxSubmitted(
                firstNameField.getText().trim(),
                lastNameField.getText().trim(),
                contactNumberField.getText().trim(),
                emailField.getText().trim(),
                this,
                event));
        rightPanel.add(submitButton);

        this.setVisible(true);
    }

    public static void showJOptionPane(String action) {
        if (action.equals("fName")) {
            JOptionPane.showMessageDialog(null, "Invalid First Name. Please enter only alphabets.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (action.equals("lName")) {
            JOptionPane.showMessageDialog(null, "Invalid Last Name. Please enter only alphabets.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (action.equals("Error 1")) {
            JOptionPane.showMessageDialog(null, "Invalid Contact Number. Please enter only numbers.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (action.equals("Error 2")) {
            JOptionPane.showMessageDialog(null, "Invalid Contact Number. It should be exactly 11 digits.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (action.equals("email")) {
            JOptionPane.showMessageDialog(null, "Invalid Email Address. Please enter a valid email.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

}
