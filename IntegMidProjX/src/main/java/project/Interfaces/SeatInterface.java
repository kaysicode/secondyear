package project.Interfaces;

import project.Client.Model.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SeatInterface extends Remote {
    public void updateSeatList(List<Integer> seat) throws RemoteException;
}
