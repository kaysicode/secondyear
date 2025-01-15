import java.util.*;
/**
 * A typical Service system has servers and clients.
 * An arriving client joins the queue of clients.
 * The server serves a client following First-Come First Served discipline
 REQUIRED:
 WRITE THE ALGORITHM BELOW:

 * Runs the simulation of a service system with servers and clients.
 * The system simulates the following:
 * 1. Clients arrive at random times and join a queue.
 * 2. The server serves clients from the queue following a First-Come, First-Served (FCFS) policy.
 * 3. When a client arrives, it is added to the queue, and the queue's status is displayed.
 * 4. If the server is idle and there is a client in the queue, the server starts serving the client.
 *    - The server serves the client for a random duration, after which it becomes available again.
 *    - The status of the server (serving or idle) and the queue is displayed at each step.
 * 5. The simulation runs for a specified duration (simulationTimeDuration), checking each time unit
 *    if a client arrives or the server finishes serving a client.
 *
 * Algorithm:
 * 1. Initialize random generators for client arrival and service times.
 * 2. Set average inter-arrival time, simulation duration, and mean service time.
 * 3. Randomly generate the first client's arrival time.
 * 4. Create an empty queue for clients.
 * 5. Initialize the server to be idle at the start of the simulation.
 * 6. Loop through the simulation from time = 0 to the end of the simulation time:
 *    a. Check if a client arrives at the current time:
 *       - If so, create the client, add it to the queue, and display the queue.
 *       - Randomly generate the next client's arrival time.
 *    b. Check if the server is idle and if there are clients in the queue:
 *       - If so, assign the first client in the queue to the server and randomly generate the service time.
 *       - Set the time when the server will become available again after serving the client.
 *       - Display the server status and the updated queue.
 *    c. Check if the server finishes serving a client at the current time:
 *       - If so, mark the server as idle and display the status.
 * 7. Repeat steps 6a to 6c until the simulation ends.
 */


/*
NAME: MAYO, Kenneth Charles P.
CODE: 9458B
DATE: October 22, 2024
 */
public class QueueSimulation {
    public static void main(String[] args) {
        QueueSimulation simulation;
        try {
            simulation = new QueueSimulation();
            simulation.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    public void run() {
        java.util.Random randomArrivalGenerator = new java.util.Random();
        java.util.Random randomServiceTimeGenerator = new java.util.Random();

        int averageInterArrival = 4;
        int simulationTimeDuration = 50; // length of simulation time
        double meanServiceTime = 5;

        // Generate random arrival of the first client
        int nextArrivalTime = randomArrivalGenerator.nextInt(averageInterArrival);

        // Create an ArrayList that will hold Queue of Clients
        Queue<Client> myListOfClients = new LinkedList<>();
        int clientId = 1;

        // Instantiate the server with no assigned client
        Server server = new Server();

        //Let simulation run from time =0 to a set length of time
        for (int time = 0; time <= simulationTimeDuration; time += 1) {

            // check if a client arrives
            if (time == nextArrivalTime) {
                System.out.println("Client " + clientId + " has arrived at time = " + nextArrivalTime + ".");
                System.out.println();

                // Construct a client object and add the client object to the Queue of waiting clients
                Client newClient = new Client(clientId, nextArrivalTime);
                myListOfClients.add(newClient);

                // Show the Queue of waiting clients and the number of waiting clients
                showList(myListOfClients);
                System.out.println("Number of clients in the list = " + myListOfClients.size());
                System.out.println();

                // Prepare the id of the next client that will arrive next
                clientId += 1;
                // Generate the random arrival time of the next client
                nextArrivalTime += 1 + randomArrivalGenerator.nextInt(averageInterArrival);
                System.out.println("Next client will arrive at time= " + nextArrivalTime);
                System.out.println();
            }

            // Check if the server is idle and if there is a client waiting to be served
            if (server.isIdle() && !myListOfClients.isEmpty()) {

                // Let the server start serving the first client in the Queue
                Client clientToServe = myListOfClients.remove();
                server.setClient(clientToServe);
                server.setStartServiceTime(time);

                // Generate the random length of time for the server to serve the client
                int serveTime = randomServiceTimeGenerator.nextInt((int) meanServiceTime);
                int timeForServerToBecomeFree = time + serveTime;
                server.setStopServiceTime(timeForServerToBecomeFree);
                System.out.println("At time= " + time + " Server started serving client " + clientToServe + ".");
                System.out.println("Server will be free at time = " + timeForServerToBecomeFree);
                System.out.println();

                //Show the updated Queue of waiting clients
                showList(myListOfClients);
            }

            // Check if the server is to finish serving a client
            if (time == server.getStopServiceTime() && time > 0) {
                System.out.println("At time = " + time + " Server finished serving client " + server.getClient() + ".");
                System.out.println();

                 // Let the status of the server be idle
                server.setClient(null);
            }
        } // end of for
    } // end of run

    public void showList(Queue<Client> a) {
        System.out.println("Queue of Waiting Clients: ");
        System.out.println("--------------------------------");
        for (Client c : a) {
            System.out.println(c.toString() + " ");
        }
        System.out.println("--------------------------------");
        System.out.println();
    }
}// end of class