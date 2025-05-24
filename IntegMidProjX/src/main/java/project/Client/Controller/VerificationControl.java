package project.Client.Controller;

import project.Client.View.VerificationPage;

import javax.swing.*;

public class VerificationControl {
    public static void disableFields(JTextField[] ipFields) {
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
    }

    public static void localhostBtn(JTextField[] ipFields, JLabel dots, JLabel dots2, JLabel dots3) {
        for (JTextField field : ipFields) field.setEnabled(false);
        for (JTextField field : ipFields) field.setVisible(false);
        dots.setVisible(false);
        dots2.setVisible(false);
        dots3.setVisible(false);
    }

    public static void ipHostBtn(JTextField[] ipFields, JLabel dots, JLabel dots2, JLabel dots3) {
        for (JTextField field : ipFields) field.setEnabled(true);
        for (JTextField field : ipFields) field.setVisible(true);
        dots.setVisible(true);
        dots2.setVisible(true);
        dots3.setVisible(true);
    }

    public static String connectBtn(JTextField[] ipFields, JRadioButton ipButton, JDialog frame) {
        String[] ipHolder = new String[1];
        if (ipButton.isSelected()) {
            StringBuilder ipAddress = new StringBuilder();
            for (JTextField field : ipFields) {
                if (field.getText().isEmpty() || Integer.parseInt(field.getText()) > 255) {
                    System.exit(0);
                    VerificationPage.jOptionPanel();
                }
                ipAddress.append(field.getText()).append(".");
            }
            ipHolder[0] = ipAddress.substring(0, ipAddress.length() - 1); // Remove trailing dot
        } else {
            ipHolder[0] = "localhost";
        }
        frame.dispose();
        return ipHolder[0];
    }
}
