package project.Client.Model;

import java.io.Serializable;

public record Event(
        String eventID,
        String eventName,
        String startTime,
        String endTime,
        int eventDay,
        String eventMonth,
        int eventYear,
        String description
) implements Serializable {

    // constructor to allow evDay as a String
    public Event(String eventID,
                 String eventName,
                 String startTime,
                 String endTime,
                 String evDay,
                 String evMonth,
                 String evYear,
                 String description) {
        this(eventID,eventName, startTime, endTime, Integer.parseInt(evDay), evMonth, Integer.parseInt(evYear), description);
    }
}
