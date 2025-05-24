package Server;

import Client.Model.Customer;
import Client.Model.Event;
import Server.XML.XMLReader;
import Server.XML.XMLWriter;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Handles client communication in a multi-threaded server environment.
 * Each instance manages a single client connection, handling data requests and updates.
 */
public class ServerThread extends Thread {
    private int counter;
    private Socket client;
    private ObjectInputStream read;
    private ObjectOutputStream write;
    private static final String FILE_PATH = "events.xml";
    private static final ReentrantLock fileLock = new ReentrantLock(); // For thread-safe file access

    /**
     * Constructs a ServerThread to handle communication with a specific client.
     *
     * @param client  The client socket.
     * @param counter The client number identifier.
     * @throws IOException If an I/O error occurs when creating input/output streams.
     */
    public ServerThread(Socket client, int counter) throws IOException {
        this.client = client;
        this.counter = counter;
        this.write = new ObjectOutputStream(client.getOutputStream()); // Initialize output stream
        this.read = new ObjectInputStream(client.getInputStream()); // Initialize input stream
        this.write.flush(); // Make it flush so that it won't stack at the server
    }

    /**
     * Runs the server thread, handling client requests and processing data accordingly.
     */
    @Override
    public void run() {
        try {
            while (true) {
                try {
                    Object request = read.readObject(); // It will receive either a String or Object

                    // Then it will go to the if else statement according to the passed request
                    if ("REQUEST_DATA".equals(request)) {
                        handleDataRequest();
                    } else if ("REQUEST_EVENT".equals(request)) {
                        handleEventRequest();
                    } else if (request instanceof List<?>) {
                        synchronized (this) {
                            handleCustomerList((List<Customer>) request);
                        }
                    } else {
                        System.out.println("Client #" + counter + " sent an unknown request: " + request);
                    }
                } catch (EOFException | SocketException e) {
                    System.out.println("Client #" + counter + " disconnected.");
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("Client #" + counter + " error: " + e.getMessage());
                }
            }
        } finally {
            closeResources();
        }
    }

    /**
     * Handles a client request for customer data.
     * Reads customer data from XML and sends it to the client.
     */
    private void handleDataRequest() {
        fileLock.lock(); // lock the method so that it only 1 thread can access it
        try {
            System.out.println("Reading XML file...");
            List<Customer> customerList = XMLReader.readXML(FILE_PATH); //read the xml and past it to the list
            if (customerList == null) { // check if the list null if then, it will initialize it
                customerList = new ArrayList<>();
            }
            write.writeObject(customerList); // pass it to the client
            write.flush(); // flush to avoid stacking in the server
            System.out.println("Sent customer list to Client #" + counter);
        } catch (IOException e) {
            System.out.println("Error sending customer list: " + e.getMessage());
        } finally {
            fileLock.unlock(); // unlock the method
        }
    }

    /**
     * Handles a client request for event data.
     * Reads event data from XML and sends it to the client.
     * <H1>Same with handleDataRequest()</H1>
     */
    private void handleEventRequest() {
        fileLock.lock();
        try {
            System.out.println("Reading XML file...");
            List<Event> eventList = XMLReader.readEvents(FILE_PATH);
            if (eventList == null) {
                eventList = new ArrayList<>();
            }
            write.writeObject(eventList);
            write.flush();
            System.out.println("Sent event list to Client #" + counter);
        } catch (IOException e) {
            System.out.println("Error sending event list: " + e.getMessage());
        } finally {
            fileLock.unlock();
        }
    }

    /**
     * Handles an incoming list of customers from the client and updates the XML file.
     *
     * @param customerList The list of customers received from the client.
     */
    private void handleCustomerList(List<Customer> customerList) {
        fileLock.lock();
        try {
            XMLWriter.customerXML(customerList, FILE_PATH);
            System.out.println("Received and processed customer list from Client #" + counter);
        } catch (Exception e) {
            System.out.println("Error processing customer list from Client #" + counter + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            fileLock.unlock();
        }
    }

    /**
     * Closes all open resources (streams and socket) when the client disconnects.
     */
    public void closeResources() {
        try {
            if (read != null) read.close();
            if (write != null) write.close();
            if (client != null) client.close();
            System.out.println("Resources cleaned up for Client #" + counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
