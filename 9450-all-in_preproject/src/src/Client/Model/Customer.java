package Client.Model;

import java.io.Serializable;

/**
 * Customer obeject where client information will be taken for 
 * other classes to use
 */
@SuppressWarnings("ALL")
public class Customer implements Serializable {
    // (e.g. E01A)
    private String eventID;

    // (e.g. Dela Cruz)
    private String lastName;

    // (e.g. Juan)
    private String firstName;

    // PH Based (e.g. 09123456789)
    private long contactNo;

    // @gmail.com based (e.g. juandelacruz@gmail.com)
    private String email;

    // (e.g. 12)
    private int seatNo;

    public Customer(String[] data, String seatNo) {
        this.eventID = data[0];
        this.lastName = data[1];
        this.firstName = data[2];
        this.contactNo = Long.parseLong(data[3]);
        this.email = data[4];
        this.seatNo = Integer.parseInt(seatNo);
    }


    public String[] toArray() {
        return new String[]{eventID, lastName, firstName, String.valueOf(contactNo), email, String.valueOf(seatNo)};
    }

    public String getEventID() {
        return eventID;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public long getContactNo() {
        return contactNo;
    }
    public String getEmail() {
        return email;
    }
    public int getSeatNo() {
        return seatNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "eventID='" + eventID + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", contactNo=" + contactNo +
                ", email='" + email + '\'' +
                ", seatNo=" + seatNo +
                '}';
    }
}
