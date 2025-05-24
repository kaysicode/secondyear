package project.Client.View;

import javax.swing.*;
import java.awt.*;

import project.Client.Controller.PasswordControl;
import project.Client.Model.Event;
import project.Client.Model.Links;

public class PasswordPage extends JFrame {

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

    // Components of Right Panel Side
    JPanel rightPanel;
    JLabel password, passwordEG;
    JTextField passwordField;
    JLabel confirmPass, confirmPassEG;
    JTextField confirmPassField;
    JButton submitButton;
    JRadioButton male;
    JRadioButton female;

    public PasswordPage(String[] data, int st, Event e) {

        // Frame
        ImageIcon imageApp = new ImageIcon(Links.APP_LOGO_ICON.getFilePath());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1491, 791);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(imageApp.getImage());
        this.setTitle("Password Page");

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

        // Line
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
                "color: #000000;'>" + e.eventName() + "</span></html>");
        eventName.setBounds(58, 520, 334, 69);
        westPanel.add(eventName);

        // Event Time (West Panel)
        time = new JLabel("<html><span style='font-family: DM Sans;" +
                "font-weight: regular; " +
                "font-size: 10px;" +
                "letter-spacing: 0.15px; " +
                "color: #000000;'> " + e.eventMonth() + " " + e.eventDay() + ", AT " + e.startTime() + "AM - " + e.endTime() + "</span></html>");
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

        // description
        description = new JLabel("<html><span style='font-family: DM Sans;" +
                "font-weight: regular; " +
                "font-size: 9px;" +
                "letter-spacing: 0.15px; " +
                "color: #000000;'><b>Event Description: </b>" + e.description() + "</span></html>");
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
                " font-size: 21px;'>CREATE PASSWORD</span></html>");
        formTitle.setBounds(50, 50, 700, 30);
        rightPanel.add(formTitle);

        // Password
        password = new JLabel("Create a Password");
        password.setFont(new Font("Roboto", Font.BOLD, 15));
        password.setBounds(150, 180, 200, 50);
        rightPanel.add(password);

        // Password Text Field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 220, 400, 40);
        passwordField.setBackground(new Color(234, 234, 234));
        passwordField.setForeground(new Color(64, 64, 64));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        rightPanel.add(passwordField);

        // Password Placeholder (example)
        passwordEG = new JLabel("Enter your First Name (e.g. JUAN)");
        passwordEG.setFont(new Font("Arial", Font.ITALIC, 10));
        passwordEG.setForeground(Color.GRAY);
        passwordEG.setBounds(150, 260, 400, 20);
        rightPanel.add(passwordEG);

        // Confirm your Password
        confirmPass = new JLabel("Confirm your Password");
        confirmPass.setFont(new Font("Roboto", Font.BOLD, 15));
        confirmPass.setBounds(150, 300, 200, 25);
        rightPanel.add(confirmPass);

        // Confirm your Password Text Field
        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(150, 330, 400, 40);
        confirmPassField.setBackground(new Color(234, 234, 234));
        confirmPassField.setForeground(new Color(64, 64, 64));
        confirmPassField.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPassField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        rightPanel.add(confirmPassField);

        // Confirm your Password Placeholder (example)
        confirmPassEG = new JLabel("Enter your Last Name (e.g. DELA CRUZ)");
        confirmPassEG.setFont(new Font("Arial", Font.ITALIC, 10));
        confirmPassEG.setForeground(Color.GRAY);
        confirmPassEG.setBounds(150, 370, 300, 20);
        rightPanel.add(confirmPassEG);

        ButtonGroup gender = new ButtonGroup();

        male = designRB("Male");
        male.setBounds(150,400,80, 50);
        rightPanel.add(male);
        gender.add(male);

        female = designRB("Female");
        female.setBounds(250, 400, 200,50);
        rightPanel.add(female);
        gender.add(female);

        // Submit Button
        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Roboto", Font.BOLD, 19));
        submitButton.setForeground(Color.BLACK);
        submitButton.setBackground(new Color(244, 17, 17, 255)); // Crimson Red
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        submitButton.setBounds(200, 560, 250, 50);
        submitButton.setFocusable(false);
        submitButton.addActionListener(e1 -> {
            PasswordControl.passChecker(
                    this,
                    passwordField.getText().trim(),
                    confirmPassField.getText().trim(),
                    data,
                    st,
                    male,
                    female,
                    e
            );
        });


        rightPanel.add(submitButton);

        this.setVisible(true);
    }

    private JRadioButton designRB(String text) {
        JRadioButton radio = new JRadioButton(text);

        // Custom square icon
        Icon squareIcon = new Icon() {
            private final int SIZE = 16;

            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(Color.BLACK);
                g.drawRect(x, y, SIZE, SIZE);
                if (((AbstractButton) c).isSelected()) {
                    g.setColor(new Color(0xB0CF8B));
                    g.fillRect(x + 2, y + 2, SIZE - 3, SIZE - 3);
                }
            }

            @Override
            public int getIconWidth() {
                return SIZE;
            }

            @Override
            public int getIconHeight() {
                return SIZE;
            }
        };

        radio.setIcon(squareIcon);
        radio.setFont(new Font("SansSerif", Font.BOLD, 16));
        radio.setOpaque(false);
        radio.setFocusPainted(false);

        return radio;
    }
}
