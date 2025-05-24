package Server;

import Server.GUI.ServerFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class Server extends Thread{
    private boolean running = false;
    private ServerSocket serverSocket;


    @Override
    public void start() {
        if (running) {
            System.out.println("Server is already running!");
            return;
        }

        running = true;
        super.start();

        try {
            serverSocket = new ServerSocket(5678);
            System.out.println("Server started! Waiting for clients...");

            int counter = 1;
            while (running) {
                try {
                    if (serverSocket.isClosed()) break;
                    Socket client = serverSocket.accept();
                    if (!running) break;

                    System.out.println("New client connected: " + counter +
                            "\n\t" + client.getInetAddress() +
                            "\n\t" + client.getLocalAddress() +
                            "\n\t" + new Date());
                    System.out.println();

                    new ServerThread(client, counter++).start();
                } catch (SocketException e) {
                    if (!running) break; // Prevents error spam when stopping
                    System.out.println("Client force stop the server: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error while accepting client connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Server could not start: " + e.getMessage());
        } finally {
            stopServer();
        }
    }


    public synchronized void stopServer() {
        if (!running) return; // Prevent multiple calls
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.out.println("Error while stopping the server: " + e.getMessage());
        }
    }

    public boolean isRunning() {
        return running;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServerFrame::new);
    }
}
