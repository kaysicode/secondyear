package Client.Model;

import Client.View.WelcomePage;
import Server.XML.XMLReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * The ClientModel class handles the client-side operations, including
 * connecting to the server, sending and receiving data, and managing
 * the list of customers and taken seats.
 */
public class ClientModel extends Thread {
    private static Socket client;
    private int port;
    private String serverAddress;
    private static ObjectInputStream read;
    private static ObjectOutputStream write;
    public static HashSet<Integer> takenSeats = new HashSet<>();
    private static List<Customer> customerList = new ArrayList<>();
    private static volatile boolean running = true;  // Add control for stopping the thread

    public ClientModel(String serverAddress, int port) throws IOException {
        this.serverAddress = serverAddress;
        this.port = port;

        checkServerAvailability();
        connectToServer();
    }

    public static HashSet<Integer> getTakenSeats() {
        return takenSeats;
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    // client to server connection
    private void connectToServer() throws IOException {
        if (client != null && !client.isClosed()) {
            client.close();
        }

        client = new Socket(serverAddress, port);
        write = new ObjectOutputStream(client.getOutputStream());
        write.flush();
        read = new ObjectInputStream(client.getInputStream()); // Reinitialize the stream
    }

    public static ObjectOutputStream getWrite() {
        return write;
    }

    public static ObjectInputStream getRead() {
        return read;
    }

    public static Socket getClient() {
        return client;
    }

    @Override
    public void run() {
        while (running) {
            try {
                write.writeObject("REQUEST_DATA");
                write.flush();

                // move the ClientController writeobject here

                Object response = read.readObject();
                if (response instanceof List<?>) {
                    customerList = (ArrayList<Customer>) response;
                    updateTakenSeats(customerList);
                } else {
                    System.err.println("Unexpected response from server.");
                }

                Thread.sleep(2000); // reduced polling interval
            } catch (IOException e) {
                System.out.println("Connection lost: " + e.getMessage());
                if (running) {
                    try {
                        System.out.println("Reconnecting to server...");
                        connectToServer();
                        System.out.println("Reconnected successfully.");
                    } catch (IOException ex) {
                        System.out.println("Failed to reconnect: " + ex.getMessage());
                        running = false;
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Server is down. Closing application.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                            System.exit(0); 
                        });
                        break;
                    }
                }
            } catch (ClassNotFoundException | InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void updateTakenSeats(List<Customer> customerList) {
        takenSeats.clear();
        for (Customer customer : customerList) {
            int seatNumber = customer.getSeatNo();
            takenSeats.add(seatNumber);
            System.out.println("Seat " + seatNumber + " marked as taken.");
        }
        System.out.println("Updated takenSeats: " + takenSeats);
    }

    public static void stopClient() {
        running = false;
        try {
            if (client != null && !client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            System.out.println("❌ Error closing client socket: " + e.getMessage());
        }
    }

    // launched app checks for server connection
    public void checkServerAvailability() {
        JDialog loadingDialog = new JDialog();
        loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        loadingDialog.setModal(false);
        loadingDialog.setUndecorated(true);
        loadingDialog.setSize(250, 120);
        loadingDialog.setLocationRelativeTo(null);

        JLabel loadingLabel = new JLabel("Connecting to server...", JLabel.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        loadingDialog.add(loadingLabel);

        loadingDialog.setVisible(true);

        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                return isServerAvailable();
            }

            @Override
            protected void done() {
                loadingDialog.dispose();
                try {
                    if (get()) {
                        // Show WelcomePage in the EDT
                        SwingUtilities.invokeLater(WelcomePage::new);
                    } else {
                        JOptionPane.showMessageDialog(null, "Server is OFF! Please start the server.",
                                "Connection Error", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }


    private boolean isServerAvailable() {
        try (Socket socket = new Socket(serverAddress, port)) {
            return socket.isConnected();
        } catch (IOException e) {
            System.err.println("Server is unavailable: " + e.getMessage());
            return false;
        }
    }
}
