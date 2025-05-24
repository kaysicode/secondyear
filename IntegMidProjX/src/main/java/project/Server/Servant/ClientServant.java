package project.Server.Servant;

import project.Client.Model.Customer;
import project.Exceptions.AlreadyLoggedInException;
import project.Exceptions.UserExistsException;
import project.Interfaces.ClientCallback;
import project.Interfaces.ClientInterface;
import project.Interfaces.SeatInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientServant extends UnicastRemoteObject implements ClientCallback {
    private final Map<String, ClientInterface> customers = new HashMap<>();
    private final List<String> cus = new ArrayList<>();
    private final Map<Integer, SeatInterface> listeners = new HashMap<>();
    private final List<Integer> seat = new ArrayList<>();

    public ClientServant() throws RemoteException{}

    @Override
    public Customer loginCall(List<Customer> u, String e, String p) throws RemoteException, UserExistsException, AlreadyLoggedInException {
        for (Customer c : u) {
            if (c.email().equals(e) && c.password().equals(p)) {
                return c;
            }
        }
        return null;
    }

    public synchronized void registerClient(String name, ClientInterface client) throws RemoteException {
        System.out.println("login " + name);

        if (!customers.containsKey(name)) {
            cus.add(name); // ✅ Only add name if it's a new login
            customers.put(name, client);
            updateAllClients();
        } else {
            System.out.println("User already registered: " + name);
        }

        for (String s : cus) {
            System.out.println(s);
        }
    }


    public synchronized List<String> getOnlineClients() throws RemoteException {
        return cus;
    }

    @Override
    public void broadcastCall(Customer u, String msg) throws RemoteException {

    }

    @Override
    public synchronized void logoutCall(String u) throws RemoteException {
        customers.remove(u);
        cus.remove(u);
        updateAllClients();
    }

    public synchronized void updateAllClients() throws RemoteException {
        List<String> names = getOnlineClients();
        for (ClientInterface client : customers.values()) {
            client.updateClientList(names);
        }
    }

    @Override
    public void registerSeat(int seatNumb ,SeatInterface list) throws RemoteException {
        System.out.println("Seat " + seatNumb);

        if (!listeners.containsKey(seatNumb)) {
            seat.add(seatNumb); // ✅ Only add name if it's a new login
            listeners.put(seatNumb, list);
            updateAllSeats(seatNumb);
        } else {
            System.out.println("Seat already taken: " + seatNumb);
        }

        for (Integer s : seat) {
            System.out.println(s);
        }
    }

    @Override
    public synchronized List<Integer> getSeat() throws RemoteException {
        for (Integer s : seat) {
            System.out.println("pogi" + s);
        }
        return seat;
    }

    @Override
    public void updateAllSeats(int seatNumber) throws RemoteException {
        List<Integer> seatTaken = getSeat();
        for (SeatInterface si : listeners.values()) {
            si.updateSeatList(seatTaken);
        }
    }

    @Override
    public synchronized void removeSeat(int seatNumber) throws RemoteException {
        listeners.remove(seatNumber);
        seat.remove((Integer) seatNumber);
        updateAllSeats(seatNumber); // Broadcast the update
    }

}
