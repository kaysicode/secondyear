package project.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface JSONInterface extends Remote {
    public <T> void writeToJSON(String filePath, T data) throws RemoteException;
    public <T> List<T> readToJSON(String filePath, Class<T> data ) throws RemoteException;
    public <T> void updateToJSON(String filePath, List<T> dataList) throws RemoteException;

}
