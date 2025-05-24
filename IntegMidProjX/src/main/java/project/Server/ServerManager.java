package project.Server;

import project.Interfaces.ClientCallback;
import project.Interfaces.EventInterface;
import project.Interfaces.JSONInterface;
import project.Server.Servant.ClientServant;
import project.Server.Servant.EventServant;
import project.Server.Servant.JSONServant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerManager {
    private Registry registry;
    private boolean running = false;

    public void startServer() throws RemoteException {
        if (running) return; // Prevent multiple instances
        try {
            EventInterface eventStub = new EventServant();
            JSONInterface jsonStub = new JSONServant();
            ClientCallback clientStub = new ClientServant();

            registry = LocateRegistry.createRegistry(1099);
            registry.rebind("eventRMI", eventStub);
            registry.rebind("jsonRMI", jsonStub);
            registry.rebind("clientRMI", clientStub);

            System.out.println("RMI Server is established!!!");
        } catch (RemoteException e) {
            throw new RemoteException("Can't send the stubs", e);
        }
    }

    public void stopServer() throws RemoteException {
        try {
            if (registry != null) {
                UnicastRemoteObject.unexportObject(registry, true);
                System.out.println("RMI Server has been stopped.");
            }
        } catch (Exception e) {
            throw new RemoteException("Error stopping the server", e);
        }
    }

    public boolean isRunning() {
        return running;
    }
}
