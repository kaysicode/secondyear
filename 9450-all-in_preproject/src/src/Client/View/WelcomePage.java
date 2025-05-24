package Client.View;

import Client.Controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    public WelcomePage() {
        JFrame frame = new JFrame("Welcome!!");
        //resizing and importing the logo
        ImageIcon logoImgRaw = new ImageIcon(("src/src/Res/App Logo (WO BG).png"));
        ImageIcon logoImg = new ImageIcon(logoImgRaw.getImage().getScaledInstance(168, 168, Image.SCALE_SMOOTH));

        //resizing and importing the icon
        ImageIcon ImageTempRaw = new ImageIcon("src/src/Res/stage3.png");
        ImageIcon ImageTemp = new ImageIcon(ImageTempRaw.getImage().getScaledInstance(604, 430, Image.SCALE_SMOOTH));

        //logo label
        JLabel imgLabel = new JLabel(logoImg);
        imgLabel.setBounds(54, 19, 168, 168);

        //title label
        JLabel titleLabel = new JLabel("FLOWEVENT");
        titleLabel.setFont(new Font("DM Sans", Font.BOLD, 27));
        titleLabel.setForeground(new Color(0XB0CF8B));
        titleLabel.setBounds(214, 67, 350, 53);

        //temp Img label
        JLabel tempImgLabel = new JLabel(ImageTemp);
        tempImgLabel.setBounds(94, 188, 564, 430);

        //txts
        JLabel grpTxt = new JLabel("Team ALL IN");
        grpTxt.setFont(new Font("DM Sans", Font.PLAIN, 16));
        grpTxt.setBounds(720, 188, 200, 25);

        JLabel contentTitleTxt = new JLabel("<html>Welcome to Our Event<br>Management System!</html>");
        contentTitleTxt.setFont(new Font("DM Sans", Font.BOLD, 35));
        contentTitleTxt.setBounds(720, 188 + 25, 400, 92);

        contentTitleTxt.setBackground(null);

        JLabel description = new JLabel("<html>Designed for small, venue-specific events, our system" +
                "<br>streamlines participant registration, ensuring accurate data" +
                "<br>management. Whether it's literary gatherings, live music, or" +
                "<br>sponsored events, our platform enhances attendee experience" +
                "<br>with efficient registration and engagement tools.</html>"
        );
        description.setFont(new Font("DM Sans", Font.PLAIN, 20));
        description.setBounds(720, 188 + 120, 700, 200);
        description.setBackground(null); // To make the background transparent

        JButton regBtn = new JButton("Register to Event now!");
        regBtn.setBounds(720, 538, 200, 50);
        regBtn.setFont(new Font("Poppins", Font.BOLD, 12));
        regBtn.setBackground(new Color(0XB0CF8B));
        regBtn.setForeground(Color.WHITE);
        regBtn.setFocusable(false);
        regBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        regBtn.addActionListener(e -> ClientController.registerButton("register", frame));

        JButton extBtn = new JButton("Exit?");
        extBtn.setBounds(930, 538, 150, 50);
        extBtn.setFont(new Font("Poppins", Font.BOLD, 12));
        extBtn.setBackground(Color.WHITE);
        extBtn.setForeground(Color.BLACK);
        extBtn.setFocusable(false);
        extBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        extBtn.addActionListener(e -> ClientController.registerButton("exit", frame));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1491, 791);
        mainPanel.add(imgLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(tempImgLabel);
        mainPanel.add(grpTxt);
        mainPanel.add(contentTitleTxt);
        mainPanel.add(description);
        mainPanel.add(regBtn);
        mainPanel.add(extBtn);


        ImageIcon imageApp = new ImageIcon("src/src/Res/App Logo (ICON).png");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1491, 791);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(imageApp.getImage());
        frame.setVisible(true);
        frame.setLayout(null);
        frame.add(mainPanel);
    }
}
