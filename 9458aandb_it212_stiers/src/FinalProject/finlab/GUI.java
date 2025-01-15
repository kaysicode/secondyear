package finlab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    JPanel panel;
    JLabel label;
    JButton enter;
    JButton exit;


    public GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("HI");
        this.setLayout(null);

        panel = new JPanel();
        panel.setBounds(20, 30, 345, 450);
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        this.add(panel);

        label = new JLabel("WELCOME!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setBounds(20, 0, 50, 80);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);

        panel.add(label);

        enter = new JButton("ENTER");
        enter.setFocusable(false);
        enter.setBackground(Color.LIGHT_GRAY);
        enter.setFont(new Font("Arial", Font.BOLD, 50));
        enter.addActionListener(this);

        panel.add(enter);

        exit = new JButton("EXIT");
        exit.setFocusable(false);
        exit.setBackground(Color.LIGHT_GRAY);
        exit.setFont(new Font("Arial", Font.BOLD, 50));
        exit.addActionListener(this);

        panel.add(exit);

        this.setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == this.exit) {
            System.out.println("Closing the program...");
            System.exit(0);
        } else if (e.getSource() == this.enter) {
            this.dispose();
            new MainMenu();
        }
    }


    public static void main(String[] args) {
        new GUI();
    }
}
