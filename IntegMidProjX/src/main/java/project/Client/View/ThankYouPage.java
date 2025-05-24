package project.Client.View;

import project.Client.Controller.ThankYouControl;
import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Client.Model.Links;

import javax.swing.*;
import java.awt.*;

public class ThankYouPage extends JFrame {
    private JFrame frame;
    private JPanel textPanel, background;
    private JLabel thankYoulabel, rightSideLabel, idCode, leftSideLabel, bottomLabel, bottomLabel2,
            imageIcon, text;
    private JButton cp;
    private JLabel checkBar;

    public ThankYouPage(Customer cus, Event event) {

        // Image
        ImageIcon imageApp = new ImageIcon(Links.APP_LOGO_ICON.getFilePath());

        // Frame
        frame = new JFrame("Thank You!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1491, 791);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(imageApp.getImage());
        frame.setResizable(false);

        // Background
        background = new JPanel();
        background.setBounds(0, 0, 1491, 791);
        background.setLayout(null);
        background.setBackground(Color.WHITE);

        ImageIcon checkImage = new ImageIcon(Links.CHECK_PNG.getFilePath());
        Image prefferedSize = checkImage.getImage().getScaledInstance(94, 63, Image.SCALE_SMOOTH);
        ImageIcon newCheckImage = new ImageIcon(prefferedSize);
        checkBar = new JLabel();
        checkBar.setIcon(newCheckImage);
        checkBar.setBounds(700, 154, 94, 63);

        // Thank You
        thankYoulabel = new JLabel("Thank You!");
        thankYoulabel.setFont(new Font("DM Sans", Font.BOLD, 45));
        thankYoulabel.setBounds(630, 250, 287, 59);

        // right side label
        rightSideLabel = new JLabel("Your ID is");
        rightSideLabel.setFont(new Font("DM Sans", Font.BOLD, 35));
        rightSideLabel.setBounds(500, 320, 300, 50);

        String id = ThankYouControl.idGenerator(cus);
        idCode = new JLabel(id);
        idCode.setFont(new Font("DM Sans", Font.BOLD, 35));
        idCode.setForeground(Color.red);
        idCode.setBounds(670, 320, 300, 50);

        // left side label
        leftSideLabel = new JLabel("Please save it!");
        leftSideLabel.setFont(new Font("DM Sans", Font.BOLD, 35));
        leftSideLabel.setBounds(880, 320, 300, 50);

        // bottom label
        bottomLabel = new JLabel("Thank you for using FLOWEVENT, " +
                "we hope our system made your event management smoother and more efficient.");
        bottomLabel.setFont(new Font("DM Sans", Font.PLAIN, 21));
        bottomLabel.setBounds(220, 380, 1331, 100);

        // bottom label 2
        bottomLabel2 = new JLabel("Have a great day, and see you again soon!");
        bottomLabel2.setFont(new Font("DM Sans", Font.PLAIN, 21));
        bottomLabel2.setBounds(550, 420, 400, 100);

        // check profile button
        cp = new JButton("Check Profile!");
        cp.setBounds(653, 551, 185, 56);
        cp.setFocusable(false);
        cp.setFont(new Font("DM Sans", Font.BOLD, 15));
        cp.setForeground(Color.BLUE);
        cp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, true));
        cp.setBackground(Color.WHITE);
        cp.addActionListener(e -> ThankYouControl.profile(cus, frame, event));

        // image
        imageIcon = new JLabel();
        ImageIcon image = new ImageIcon(Links.APP_LOGO_WOBG.getFilePath());
        Image newImage = image.getImage().getScaledInstance(168, 168, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        imageIcon.setIcon(img);
        imageIcon.setBounds(54, 19, 168, 168);

        text = new JLabel("<html><span style='font-family: DM Sans; " +
                "font-weight: bold; " +
                "font-size: 27px; "
                + "letter-spacing: 0.5px; " +
                "color: #B0CF8B;'>FLOWEVENT</span></html>");
        text.setBounds(214, 67, 350, 53);

        // add the components to the background
        background.add(text);
        background.add(checkBar);
        background.add(thankYoulabel);
        background.add(rightSideLabel);
        background.add(idCode);
        background.add(leftSideLabel);
        background.add(bottomLabel);
        background.add(bottomLabel2);
        background.add(imageIcon);
        background.add(cp);
        background.add(rootPane);

        frame.add(background);
        frame.setVisible(true);
    }
}