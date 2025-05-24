/**
 * A simple server program that accepts multiple client connections and interacts with them.
 * Each client is asked for their name and age to determine if they are eligible to vote.
 */
package pexer2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The main server class that listens for incoming client connections.
 */
public class PreExercise2 {

    /**
     * The main entry point of the server.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        int port = 2000;
        System.out.println("Server is running!!");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // Accept a new client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Create a new thread to handle the client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Handles communication between the server and a single client.
 */
class ClientHandler implements Runnable {
    private Socket clientSocket;

    /**
     * Constructs a ClientHandler instance with the given client socket.
     *
     * @param clientSocket The socket associated with the connected client.
     */
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Runs the client interaction logic, asking for the user's name and age.
     */
    @Override
    public void run() {
        try (
                BufferedReader streamRdr = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter streamWtr = new PrintWriter(
                        clientSocket.getOutputStream(), true);
        ) {
            int counter = 1;
            String continuePa;

            do {
                streamWtr.println("Welcome Host " + counter);
                // Server sends message to client
                streamWtr.println("What is your name? ");

                // Server accepts input from client
                String name = streamRdr.readLine();

                int age;
                while (true) {
                    streamWtr.println("What is your age? ");
                    try {
                        age = Integer.parseInt(streamRdr.readLine());
                        if (age <= 0) {
                            throw new NumberFormatException();
                        } else {
                            break;
                        }
                    } catch (NumberFormatException nfe) {
                        streamWtr.println("Please enter a valid age.");
                        continue;
                    }
                }

                if (age >= 18) {
                    streamWtr.println(name + ", you may exercise your right to vote!");
                } else {
                    streamWtr.println(name + ", you are still too young to vote!");
                }

                // Will ask to continue, will not stop until the user enters "n"
                streamWtr.println("Still continue? [ENTER: yes] ");
                continuePa = streamRdr.readLine();

                streamWtr.println("Thank you and good day.");
                streamWtr.println();
                counter++;

            } while (continuePa.equalsIgnoreCase("Yes"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}