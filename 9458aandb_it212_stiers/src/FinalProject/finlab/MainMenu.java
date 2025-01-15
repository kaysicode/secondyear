package finlab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    JButton button1, button2, button3, button4, button5;
    JPanel panel, panel2;
    JLabel label;

    public MainMenu() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);


        // Top Panel
        panel = new JPanel();
        panel.setBackground(new Color(0X00FF7F));
        panel.setPreferredSize(new Dimension(100, 100));
        this.add(panel, BorderLayout.NORTH);

        label = new JLabel("WELCOME!", SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(label);

        // Center Panel (Buttons)
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 2, 10, 10));
        panel2.setBackground(Color.LIGHT_GRAY);
        this.add(panel2, BorderLayout.CENTER);

        button1 = new JButton("Load File containing the graph's data");
        button1.setFocusable(false);
        button1.setBackground(Color.WHITE);
        button1.setFont(new Font("Calibri", Font.BOLD, 12));
        button1.addActionListener(this);
        panel2.add(button1);

        button2 = new JButton("Perform Depth First Traversal");
        button2.setFocusable(false);
        button2.setBackground(Color.WHITE);
        button2.setFont(new Font("Calibri", Font.BOLD, 12));
        button2.addActionListener(this);
        panel2.add(button2);

        button3 = new JButton("Perform Breadth First Traversal");
        button3.setFocusable(false);
        button3.setBackground(Color.WHITE);
        button3.setFont(new Font("Calibri", Font.BOLD, 12));
        button3.addActionListener(this);
        panel2.add(button3);

        button4 = new JButton("Show Shortest Path");
        button4.setFocusable(false);
        button4.setBackground(Color.WHITE);
        button4.setFont(new Font("Calibri", Font.BOLD, 12));
        button4.addActionListener(this);
        panel2.add(button4);

        button5 = new JButton("Exit");
        button5.setFocusable(false);
        button5.setBackground(Color.WHITE);
        button5.setFont(new Font("Calibri", Font.BOLD, 12));
        button5.addActionListener(this);
        panel2.add(button5);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            JOptionPane.showMessageDialog(null, "File is Successfully Read!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == button2) {
            this.dispose();
            new DFSgui();
        } else if (e.getSource() == button3) {
            this.dispose();
            new BFSgui();
        } else if (e.getSource() == button4) {
            this.dispose();
            new DijkstraGUI();
        } else if (e.getSource() == button5) {
            System.exit(0);
        }
    }


}
