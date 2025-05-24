package project.Client.View;

import project.Client.Controller.LoginControl;
import project.Client.Controller.WelcomeControl;
import project.Client.Model.Links;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class LoginPage extends JFrame {

    JPanel panel;
    JPanel line;

    // Label
    JLabel title;
    JLabel text;
    JLabel name;
    JLabel password;
    JLabel register;

    // TextField
    JTextField nameTF;
    JTextField passwordTF;

    // Button
    JButton loginBtn;
    JButton registerBtn;

    public LoginPage () {

        // Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(368, 604);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login");

        // ICON
        ImageIcon icon = new ImageIcon(Links.APP_LOGO_ICON.getFilePath());
        this.setIconImage(icon.getImage());

        // Main Panel
        panel = new JPanel();
        panel.setBounds(0,0,368,604);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

        // Title
        title = new JLabel("WELCOME!!!");
        title.setBounds(69, 41, 231, 37);
        title.setFont(new Font("Roboto", Font.BOLD, 32));
        panel.add(title);

        // Text
        text = new JLabel("Please log in to continue");
        text.setBounds(86, 78, 197, 22);
        text.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.add(text);

        // Name
        name = new JLabel("First Name and Last Name");
        name.setBounds(25, 132, 176, 27);
        name.setFont(new Font("Roboto", Font.PLAIN, 14));
        name.setForeground(Color.GRAY);
        panel.add(name);

        // Name - TextField
        nameTF = new JTextField();
        nameTF.setBounds(25, 163, 300, 48);
        nameTF.setBackground(new Color(234, 234, 234));
        nameTF.setForeground(new Color(64, 64, 64));
        nameTF.setFont(new Font("Roboto", Font.PLAIN, 14));
        nameTF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        panel.add(nameTF);

        // Password
        password = new JLabel("Password");
        password.setBounds(25, 226, 176, 27);
        password.setFont(new Font("Roboto", Font.PLAIN, 14));
        password.setForeground(Color.GRAY);
        panel.add(password);

        // Password - TextField
        passwordTF = new JTextField();
        passwordTF.setBounds(25, 257, 300, 48);
        passwordTF.setBackground(new Color(234, 234, 234));
        passwordTF.setForeground(new Color(64, 64, 64));
        passwordTF.setFont(new Font("Roboto", Font.PLAIN, 14));
        passwordTF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        panel.add(passwordTF);

        // Login Button
        loginBtn = new JButton("Log In");
        loginBtn.setBounds(25, 337, 300, 48);
        loginBtn.setFocusable(false);
        loginBtn.setFont(new Font("Roboto", Font.PLAIN, 16));
        loginBtn.setBackground(new Color(0x0F62FE));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(e -> LoginControl.checkAccount(
                nameTF.getText().trim(),
                passwordTF.getText().trim(),
                this
        ));
        panel.add(loginBtn);

        // Horizontal Line
        line = new JPanel();
        line.setBounds(17, 431, 315, 1);
        line.setBackground(Color.LIGHT_GRAY);
        panel.add(line);

        // Register Text
        register = new JLabel("No account yet? Register Now!");
        register.setBounds(76, 445, 200, 20);
        register.setFont(new Font("Roboto", Font.PLAIN, 14));
        register.setForeground(Color.GRAY);
        panel.add(register);

        // Register Button
        registerBtn = new JButton("Register Now");
        registerBtn.setBounds(25, 490, 300, 48);
        registerBtn.setFocusable(false);
        registerBtn.setFont(new Font("Roboto", Font.PLAIN, 16));
        registerBtn.setBackground(new Color(0xFF0C10));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.addActionListener(e -> {
            try {
                WelcomeControl.getActionButton("GO_TO_WELCOME", this);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(registerBtn);


        this.setVisible(true);
    }
}
