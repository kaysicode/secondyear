package Server.GUI;

import Server.Server;

import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {
    public static Server server;
    JButton[] button;
    private JLabel imgLabel;
    private JPanel greenPanel;
    private JPanel sidePanel;
    public JPanel contentPanel;
    private ConsolePage consoleView;//view


    public ServerFrame() {
        int jbX = 17, jbY = 217, jbW = 161, jbH = 42;
        String[] buttonNames = {"Start", "Console Log", "Create Event", "View Data"};

        // Logo
        ImageIcon logoImgRaw = new ImageIcon(("src/src/Res/App Logo (WO BG).png"));
        ImageIcon logoImg = new ImageIcon(logoImgRaw.getImage().getScaledInstance(168, 168, Image.SCALE_SMOOTH));

        // Logo label
        imgLabel = new JLabel(logoImg);
        imgLabel.setBounds(10, 0, 178, 168);
        greenPanel = new JPanel();
        greenPanel.setBounds(207, 0, 2, 791);
        greenPanel.setBackground(new Color(176, 207, 139));

        // Side panel
        sidePanel = new JPanel();
        sidePanel.add(imgLabel);
        sidePanel.setBounds(0, 0, 207, 791);
        sidePanel.setLayout(null);
        button = new JButton[4];
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton(buttonNames[i]);
            button[i].setBounds(jbX, jbY += jbH + 10, jbW, jbH);
            button[i].setBackground(Color.LIGHT_GRAY);
            button[0].setBackground(Color.GREEN);
            button[i].setForeground(Color.WHITE);
            button[i].setFont(new Font("DM Sans", Font.PLAIN, 15));
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button[i].setFocusable(false);
            button[i].setEnabled(false);
            sidePanel.add(button[i]);
        }

        // Console view setup
        consoleView = new ConsolePage();
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(232, 33, 1231, 593);
        contentPanel.setBackground(Color.gray);
        contentPanel.add(consoleView);

        // Wrap ConsolePage into the JScrollPane
        JScrollPane scrollPane = new JScrollPane(consoleView);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 10, 1211, 573);

        // Redirect system output to consoleView
        System.setOut(consoleView.getPrintStream());
        System.setErr(consoleView.getPrintStream());

        // Button actions
        button[0].setEnabled(true);
        button[0].addActionListener(e -> {//start / stop
            if (server == null || !server.isRunning()) {
                server = new Server();
                new Thread(() -> server.start()).start();
                button[0].setText("Stop");
                button[0].setBackground(Color.RED);
                button[1].setEnabled(true);
                button[1].setBackground(new Color(176, 207, 139));
                button[2].setEnabled(true);
                button[2].setBackground(new Color(176, 207, 139));
                button[3].setEnabled(true);
                button[3].setBackground(new Color(176, 207, 139));
            } else {
                System.exit(0); // exit
            }
        });

        button[1].addActionListener(e -> { //consoles
            contentPanel.removeAll();
            contentPanel.add(scrollPane);
            contentPanel.revalidate();
            contentPanel.repaint();
            button[0].setEnabled(true);
            button[1].setEnabled(false);
            button[2].setEnabled(true);
            button[3].setEnabled(true);
        });

        button[2].addActionListener(e -> { //Add event
            contentPanel.removeAll();
            contentPanel.add(new AddEventPage());
            contentPanel.revalidate();
            contentPanel.repaint();
            button[0].setEnabled(true);
            button[1].setEnabled(true);
            button[2].setEnabled(false);
            button[3].setEnabled(true);
        });

        button[3].addActionListener(e -> { //show Data
            contentPanel.removeAll();
            contentPanel.add(new DataPage());
            button[0].setEnabled(true);
            button[1].setEnabled(true);
            button[2].setEnabled(true);
            button[3].setEnabled(false);
            contentPanel.revalidate();
            contentPanel.repaint();
        });


        // Frame setup
        this.setLayout(null);
        this.add(sidePanel);
        this.add(greenPanel);
        this.add(contentPanel);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1491, 791);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
