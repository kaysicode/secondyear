import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

public interface MidInterface2C extends Remote {
    String dotw(int m, int d, int y) throws RemoteException;
    int age(Calendar calendar) throws RemoteException;
    boolean isLeapYear(int year) throws RemoteException;

}
