package project.Server.Servant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project.Interfaces.JSONInterface;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class JSONServant extends UnicastRemoteObject implements JSONInterface {

    public JSONServant() throws RemoteException {}

    @Override
    public <T> void writeToJSON(String filePath, T data) throws RemoteException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<T> dataList = new ArrayList<>();

        try {
            dataList = (List<T>) readToJSON(filePath, data.getClass());
            if (dataList.contains(data)){
                System.out.println("Duplicate found. Not adding to JSON.");
                return;
            }

            dataList.add(data);

            try (Writer write = new FileWriter(filePath)) {
                gson.toJson(dataList, write);
            }

            System.out.println("New data added to JSON file: " + filePath);

        } catch (IOException e) {
            throw new RemoteException("Error writing to JSON File", e);
        }
    }

    @Override
    public <T> List<T> readToJSON(String filePath, Class<T> data) throws RemoteException {
        Gson gson = new Gson();
        List<T> dataList = new ArrayList<>();

        try (Reader read = new FileReader(filePath)) {
            Type type = (Type) TypeToken.getParameterized(List.class, data).getType();
            dataList = gson.fromJson(read, type);

            if (dataList == null) {
                dataList = new ArrayList<>();
            }

        } catch (IOException e) {
            throw new RemoteException("Error reading JSON File", e);
        }

        return dataList;
    }

    @Override
    public <T> void updateToJSON(String filePath, List<T> dataList) throws RemoteException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer write = new FileWriter(filePath)) {
            gson.toJson(dataList, write);
        } catch (IOException e) {
            throw new RemoteException("Error writing full list to JSON", e);
        }
    }
}
