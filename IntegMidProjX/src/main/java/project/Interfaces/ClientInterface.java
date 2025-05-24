package project.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientInterface extends Remote {
    public void updateClientList(List<String> clients) throws RemoteException;
}
