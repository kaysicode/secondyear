package project.Client.View;

import project.Client.Controller.VerificationControl;
import project.Client.Model.Links;

import javax.swing.*;
import java.awt.*;

public class VerificationPage {

    public static String Verification() {

        // Create a JDialog as the main UI
        JDialog dialog = new JDialog((Frame) null, "Verification", true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(null);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 300);
        panel.setBackground(new Color(0xB0CF8B));
        panel.setLayout(null);

        // App Logo Label
        JLabel imageLabel = new JLabel();
        ImageIcon image = new ImageIcon(Links.APP_LOGO_WOBG.getFilePath());
        Image newImage = image.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon imh = new ImageIcon(newImage);
        imageLabel.setIcon(imh);
        imageLabel.setBounds(10, 10, 80, 80);
        panel.add(imageLabel);

        // Label for the Text "SELECT CONNECTION TYPE"
        JLabel label = new JLabel("SELECT CONNECTION TYPE");
        label.setFont(new Font("Calibri", Font.BOLD, 16));
        label.setBounds(100, 20, 200, 40);
        panel.add(label);

        // Radio Buttons
        JRadioButton localhostButton = new JRadioButton("Localhost", true);
        localhostButton.setFocusable(false);
        localhostButton.setBackground(new Color(0xB0CF8B));
        JRadioButton ipButton = new JRadioButton("Other IP Address");
        ipButton.setFocusable(false);
        ipButton.setBackground(new Color(0xB0CF8B));

        // Button Group for that only 1 Radio Button can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(localhostButton);
        group.add(ipButton);
        localhostButton.setBounds(60, 100, 100, 30);
        ipButton.setBounds(180, 100, 150, 30);
        panel.add(localhostButton);
        panel.add(ipButton);

        // Text Field for the IP Addresses
        JTextField[] ipFields = new JTextField[4];
        int xPosition = 70;
        for (int i = 0; i < 4; i++) {
            ipFields[i] = new JTextField();
            ipFields[i].setBounds(xPosition, 140, 50, 30);
            panel.add(ipFields[i]);
            xPosition += 60;
        }
        // Call the Controller for the logic for the text field
        VerificationControl.disableFields(ipFields);

        // Label for the Dots
        JLabel dots = new JLabel("."), dots2 = new JLabel("."), dots3 = new JLabel(".");
        dots.setFont(new Font("Gothic Century", Font.BOLD, 25));
        dots2.setFont(new Font("Gothic Century", Font.BOLD, 25));
        dots3.setFont(new Font("Gothic Century", Font.BOLD, 25));
        dots.setBounds(122, 140, 10, 30);
        dots2.setBounds(182, 140, 10, 30);
        dots3.setBounds(242, 140, 10, 30);

        // Call the Controller for the logic of each Radio Button
        localhostButton.addActionListener(e -> VerificationControl.localhostBtn(ipFields, dots, dots2, dots3));
        ipButton.addActionListener(e -> VerificationControl.ipHostBtn(ipFields, dots, dots2, dots3));

        // Add the labels "DOTS" in the Main Panel
        panel.add(dots);
        panel.add(dots2);
        panel.add(dots3);

        // Set the visible of each dots to false ("INVISIBLE")
        dots.setVisible(false);
        dots2.setVisible(false);
        dots3.setVisible(false);

        // Connect Button
        JButton connectButton = new JButton("Connect");
        connectButton.setFocusable(false);
        connectButton.setBackground(Color.WHITE);
        connectButton.setBounds(140, 200, 100, 30);
        panel.add(connectButton);

        dialog.add(panel);

        final String[] selectedIP = { "localhost" }; // Default

        // Close the dialog when the Connect button is clicked
        connectButton.addActionListener(e -> {
            selectedIP[0] = VerificationControl.connectBtn(ipFields, ipButton, dialog);
            dialog.dispose();
        });

        // Prevent closing unless "Connect" is clicked
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(dialog,
                        "Are you sure you want to exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0); // Close the program
                }
            }
        });

        dialog.setVisible(true); // Show the dialog

        return selectedIP[0]; // Return the selected IP after user clicks "Connect"
    }

    public static void jOptionPanel() {
        JOptionPane.showMessageDialog(null, "Invalid IP Address", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
