package project.Client.Model;

import java.io.Serializable;

public record Customer(
        String lastName,
        String firstName,
        long contactNo,
        String email,
        int seatNo,
        String eventID,
        String password,
        String gender
) implements Serializable {

    // Custom constructor to accept a String[] and a seatNo as String
    public Customer(String[] data, int seatNo, String password, String gender) {
        this(
                data[0],                      // lastName
                data[1],                      // firstName
                Long.parseLong(data[2]),      // contactNo
                data[3],                      // email
                seatNo,                       // seatNo
                data[4],                      // eventID
                password,                     // password
                gender                        // gender
        );
    }

    // Method to return fields as array
    public String[] toArray() {
        return new String[]{
                eventID, lastName, firstName,
                String.valueOf(contactNo), email,
                String.valueOf(seatNo)
        };
    }

}
