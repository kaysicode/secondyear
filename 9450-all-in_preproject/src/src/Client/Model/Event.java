package Client.Model;

import java.io.Serializable;

/**
 * Event details will be handled here and distributed across 
 * the server and client programs
 */
public class Event implements Serializable {
    private String eventID;
    private String eventName;
    private String startTime;
    private String endTime;
    private int evDay;
    private String evMonth;
    private String description;

    public Event(String eventID, String eventName,String startTime, String endTime, String evDay, String evMonth, String description) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.evDay = Integer.parseInt(evDay);
        this.evMonth = evMonth;
        this.description = description;
    }

    public String getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartTime() {return startTime;}

    public String getEndTime() {return endTime;}

    public int getEvDay() {
        return evDay;
    }

    public String getEvMonth() {
        return evMonth;
    }

    public String getDescription(){
        return description;
    }

}

