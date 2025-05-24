package project.Interfaces;

import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Exceptions.AlreadyLoggedInException;
import project.Exceptions.UserExistsException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientCallback extends Remote {
    public Customer loginCall(List<Customer> u, String e, String p) throws RemoteException, UserExistsException, AlreadyLoggedInException;

    public void registerClient(String name, ClientInterface client) throws RemoteException;

    public List<String> getOnlineClients() throws RemoteException;

    public void broadcastCall(Customer u, String msg) throws RemoteException;

    public void logoutCall(String u) throws RemoteException;

    public void updateAllClients() throws RemoteException;

    public void registerSeat(int seatNumb ,SeatInterface list) throws RemoteException;

    public List<Integer> getSeat() throws RemoteException;

    public void updateAllSeats(int seatNumber) throws RemoteException;

    public void removeSeat(int seatNumber) throws RemoteException;

}
