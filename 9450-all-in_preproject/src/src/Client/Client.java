package Client;

import Client.Model.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The Client class serves as the entry point for the client application.
 * It provides a GUI for selecting the connection type (localhost or custom IP address)
 * and initiates a connection to the server.
 */
@SuppressWarnings("ALL")
public class Client {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::Verification);
    }

    /**
     * A GUI method for verifying the connection type.
     *
     * @author Kenneth Charles P. Mayo
     * @since 2025-03-05
     */
    public static void Verification() {
        // Frame
        JFrame frame = new JFrame("Verification");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Icon Image
        ImageIcon icon = new ImageIcon("src/src/Res/App Logo (ICON).png");
        frame.setIconImage(icon.getImage());

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBounds(0,0, 400, 300);
        panel.setBackground(new Color(0xB0CF8B));
        panel.setLayout(null);

        // App Logo Label
        JLabel imageLabel = new JLabel();
        ImageIcon image = new ImageIcon("src/src/Res/App Logo (WO BG).png");
        Image newImage = image.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon imh = new ImageIcon(newImage);
        imageLabel.setIcon(imh);
        imageLabel.setBounds(10, 10, 80, 80);
        panel.add(imageLabel);


        // Text Label
        JLabel label = new JLabel("SELECT CONNECTION TYPE");
        label.setFont(new Font("Calibri", Font.BOLD, 16));
        label.setBounds(100, 20, 200, 40);
        panel.add(label);

        // Button for the Localhost
        JRadioButton localhostButton = new JRadioButton("Localhost", true);
        localhostButton.setBackground(new Color(0xB0CF8B));
        localhostButton.setFocusable(false);
        localhostButton.setBounds(60, 100, 100, 30);

        // Button for the Other IP
        JRadioButton ipButton = new JRadioButton("Other IP Address");
        ipButton.setBackground(new Color(0xB0CF8B));
        ipButton.setFocusable(false);
        ipButton.setBounds(180, 100, 150, 30);

        // ButtonGroup so that only one button can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(localhostButton);
        group.add(ipButton);

        panel.add(localhostButton);
        panel.add(ipButton);

        // Create JTextFields for the IP address
        JTextField[] ipFields = new JTextField[4];  // Updated to 4 fields
        int xPosition = 70;
        for (int i = 0; i < 4; i++) {
            ipFields[i] = new JTextField();
            ipFields[i].setBounds(xPosition, 140, 50, 30);
            panel.add(ipFields[i]);
            xPosition += 60;
        }

        // Add dot labels between the IP fields
        JLabel dots = new JLabel(".");
        dots.setFont(new Font("Gothic Century", Font.BOLD, 25));
        dots.setBounds(122, 140, 10, 30);
        panel.add(dots);

        JLabel dots2 = new JLabel(".");
        dots2.setBounds(182, 140, 10, 30);
        dots2.setFont(new Font("Gothic Century", Font.BOLD, 25));
        panel.add(dots2);

        JLabel dots3 = new JLabel(".");
        dots3.setBounds(242, 140, 10, 30);  // Added the third dot
        dots3.setFont(new Font("Gothic Century", Font.BOLD, 25));
        panel.add(dots3);

        dots.setVisible(false);
        dots2.setVisible(false);
        dots3.setVisible(false);

        // Disable IP fields initially
        for (JTextField field : ipFields) {
            field.setVisible(false);
            field.setEnabled(false);
            field.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    char c = evt.getKeyChar();
                    if (!Character.isDigit(c) || field.getText().length() >= 3) {
                        evt.consume();
                    }
                }
            });
        }

        ipButton.addActionListener(e -> {
            for (JTextField field : ipFields) field.setEnabled(true);
            for (JTextField field : ipFields) field.setVisible(true);
            dots.setVisible(true);
            dots2.setVisible(true);
            dots3.setVisible(true);
        });
        localhostButton.addActionListener(e -> {
            for (JTextField field : ipFields) field.setEnabled(false);
            for (JTextField field : ipFields) field.setVisible(false);
            dots.setVisible(false);
            dots2.setVisible(false);
            dots3.setVisible(false);
        });

        JButton connectButton = new JButton("Connect");
        connectButton.setBounds(140, 200, 100, 30);
        connectButton.setBackground(Color.WHITE);
        connectButton.setFocusable(false);
        panel.add(connectButton);

        connectButton.addActionListener(e -> {
            String ip = "localhost";
            if (ipButton.isSelected()) {
                StringBuilder ipAddress = new StringBuilder();
                for (JTextField field : ipFields) {
                    if (field.getText().isEmpty() || Integer.parseInt(field.getText()) > 255) {
                        JOptionPane.showMessageDialog(frame, "Invalid IP Address", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ipAddress.append(field.getText()).append(".");
                }
                ip = ipAddress.substring(0, ipAddress.length() - 1);  // Remove the trailing dot
            }
            frame.dispose();
            try {
                new ClientModel(ip, 5678).start();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
