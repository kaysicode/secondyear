package Server.XML;

import Client.Model.Customer;
import Client.Model.Event;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLReader {
    public static List<Customer> readXML(String filePath) {
        List<Customer> customerList = new ArrayList<>();

        try {
            // Initialize Document Builder
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Normalize the XML Structure
            document.getDocumentElement().normalize();

            // Read <Customer1> nodes
            NodeList customerNodes = document.getElementsByTagName("Customer1");
            for (int i = 0; i < customerNodes.getLength(); i++) {
                Node node = customerNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Extract customer details
                    String eventID = getElementText(element, "Event_ID");
                    String lastName = getElementText(element, "Last_Name");
                    String firstName = getElementText(element, "First_Name");
                    String contactNumber = getElementText(element, "Contact_Number");
                    String email = getElementText(element, "Email");
                    String seat = getElementText(element, "Seat");

                    String[] cusData = {eventID, lastName, firstName, contactNumber, email};
                    Customer customer = new Customer(cusData, seat);
                    customerList.add(customer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerList;
    }

    /**
     * Reads event data from the specified XML file and returns a list of events.
     *
     * @param filePath the path to the XML file containing event data
     * @return a list of {@link Event} objects parsed from the XML file
     */
    public static List<Event> readEvents(String filePath) {
        List<Event> eventList = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList eventNodes = document.getElementsByTagName("Event");
            for (int i = 0; i < eventNodes.getLength(); i++) {
                Node node = eventNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String eventID = getElementText(element, "Event_ID");
                    String eventName = getElementText(element, "Event_Name");
                    String startTime = getElementText(element, "Start_Time");
                    String endTime = getElementText(element, "End_Time");
                    String eventDay = getElementText(element, "Event_Day");
                    String eventMonth = getElementText(element, "Event_Month");
                    String description = getElementText(element, "Event_Description");

                    Event event = new Event(eventID, eventName, startTime, endTime,
                            eventDay, eventMonth, description);
                    eventList.add(event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventList;
    }
    /**
     * Retrieves the text content of an XML element by its tag name.
     *
     * @param element the parent XML element
     * @param tagName the name of the tag to retrieve the text from
     * @return the text content of the specified tag, or an empty string if not found
     */
    private static String getElementText(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        if (nodes.getLength() > 0 && nodes.item(0) != null) {
            return nodes.item(0).getTextContent();
        }
        return "";
    }
    /**
     * Parses a comma-separated string of seat numbers into an integer array.
     *
     * @param seatNO the comma-separated seat numbers as a string
     * @return an array of integers representing seat numbers, or an empty array if parsing fails
     */
    private static int[] parseSeatNumbers(String seatNO) {
        if (seatNO == null || seatNO.isEmpty()) {
            return new int[0];
        }

        try {
            String[] parts = seatNO.split(",");
            int[] seats = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                seats[i] = Integer.parseInt(parts[i].trim());
            }
            return seats;
        } catch (NumberFormatException e) {
            System.err.println("Invalid seat number format: " + seatNO);
            return new int[0];
        }
    }
}