package Midterms;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MidExamA extends Remote {
    int sumResult(int begValue, int endValue) throws RemoteException;
    int digitalRoot(int value) throws RemoteException;
    int digitalRoot2(int value) throws RemoteException;
    int digitalRootNiRobe(int value) throws RemoteException;
}
