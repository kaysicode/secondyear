package project.Server;

import project.Server.ServerGUI.ServerFrame;

import javax.swing.*;

public class ServerRMI {

    public ServerRMI() {
        // Create a ServerManager instance
        ServerManager serverManager = new ServerManager();

        // Start GUI and pass the server manager
        SwingUtilities.invokeLater(() -> new ServerFrame(serverManager));
    }

    public static void main(String[] args) {
        new ServerRMI();
    }
}
