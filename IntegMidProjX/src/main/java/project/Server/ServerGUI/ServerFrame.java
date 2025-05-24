package project.Server.ServerGUI;

import project.Client.Model.Links;
import project.Server.ServerManager;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerFrame extends JFrame {
    private JButton[] button;
    private JLabel imgLabel;
    private JPanel greenPanel, sidePanel, contentPanel;
    private JLabel ipLabel;
    private ConsolePanel consoleView;
    private ServerManager serverManager;

    public ServerFrame(ServerManager serverManager) {
        this.serverManager = serverManager;

        int jbX = 17, jbY = 217, jbW = 161, jbH = 42;
        String[] buttonNames = {"Start", "Console Log", "Create Event", "View Data"};

        // Get IP Address
        String ipAddress = "Unknown";
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // IP Address Label
        ipLabel = new JLabel("Server's IP Address: " + ipAddress);
        ipLabel.setBounds(232, 5, 300, 25);
        ipLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Logo
        ImageIcon logoImgRaw = new ImageIcon("src/src/Res/App Logo (WO BG).png");
        ImageIcon logoImg = new ImageIcon(logoImgRaw.getImage().getScaledInstance(168, 168, Image.SCALE_SMOOTH));
        imgLabel = new JLabel(logoImg);
        imgLabel.setBounds(10, 0, 178, 168);

        // Side panel
        greenPanel = new JPanel();
        greenPanel.setBounds(207, 0, 2, 791);
        greenPanel.setBackground(new Color(176, 207, 139));

        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 0, 207, 791);
        sidePanel.add(imgLabel);

        // Buttons
        button = new JButton[4];
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton(buttonNames[i]);
            button[i].setBounds(jbX, jbY += jbH + 10, jbW, jbH);
            button[i].setBackground(Color.LIGHT_GRAY);
            button[i].setForeground(Color.WHITE);
            button[i].setFont(new Font("DM Sans", Font.PLAIN, 15));
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button[i].setFocusable(false);
            button[i].setEnabled(false);
            sidePanel.add(button[i]);
        }

        // Start button action
        button[0].setEnabled(true);
        button[0].setBackground(Color.GREEN);
        button[0].addActionListener(e -> {
            if (button[0].getText().equals("Start")) {
                button[0].setText("Stop");
                button[0].setBackground(Color.RED);

                // Enable buttons
                for (int i = 1; i < button.length; i++) {
                    button[i].setEnabled(true);
                    button[i].setBackground(new Color(176, 207, 139));
                }

                // Start the RMI Server
                try {
                    serverManager.startServer();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error starting the server: " + ex.getMessage(),
                            "Server Error", JOptionPane.ERROR_MESSAGE);
                }

            } else { // Stop Server
                try {
                    serverManager.stopServer();
                    button[0].setText("Start");
                    button[0].setBackground(new Color(176, 207, 139));
                    button[0].setEnabled(true);

                    // Disable other buttons
                    for (int i = 1; i < button.length; i++) {
                        button[i].setEnabled(false);
                        button[i].setBackground(Color.LIGHT_GRAY);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error stopping the server: " + ex.getMessage(),
                            "Server Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Console view setup
        consoleView = new ConsolePanel();
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(232, 33, 1231, 593);
        contentPanel.setBackground(Color.GRAY);
        contentPanel.add(consoleView);

        // Console Log Scroll Pane
        JScrollPane scrollPane = new JScrollPane(consoleView);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 10, 1211, 573);

        // Redirect system output to consoleView
        System.setOut(consoleView.getPrintStream());
        System.setErr(consoleView.getPrintStream());

        // Console Log button action
        button[1].addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(scrollPane);
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        // Add Event button action
        button[2].addActionListener(e -> {
            contentPanel.removeAll();
//            contentPanel.add(new AddEventPage());
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        // View Data button action
        button[3].addActionListener(e -> {
            contentPanel.removeAll();
//            contentPanel.add(new DataPage());
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        Image imageIcon = new ImageIcon(Links.APP_LOGO_ICON.getFilePath()).getImage();
        // Frame setup
        this.setLayout(null);
        this.add(ipLabel);
        this.add(sidePanel);
        this.add(greenPanel);
        this.add(contentPanel);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1491, 791);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setIconImage(imageIcon);
    }
}
