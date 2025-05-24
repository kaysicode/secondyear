package Server.XML;

import Client.Model.Customer;
import Client.Model.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLWriter {

    public static void eventXML(List<Event> eventList, String filePath) {
        try {
            Document doc = getDocument(filePath);
            Element rootElement = doc.getDocumentElement();

            for (Event event : eventList) {
                Element eventElement = createEventElement(doc, event);
                rootElement.appendChild(eventElement);
            }

            writeXmlFile(doc, new File(filePath));
            System.out.println(" Data successfully written to XML!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void customerXML(List<Customer> userList, String filePath) {
        try {
            Document doc = getDocument(filePath);
            Element rootElement = doc.getDocumentElement();

            int customerCount = 1; // Ensure proper numbering
            for (Customer cus : userList) {
                Element customerElement = createUserElement(doc, cus, customerCount++);
                rootElement.appendChild(customerElement);
            }

            writeXmlFile(doc, new File(filePath));
            System.out.println("✅ Data successfully written to XML!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes a node from the XML file based on a specified attribute.
     * @param tagName The name of the tag to search for.
     * @param attrName The attribute name to check.
     * @param attrValue The value of the attribute to match.
     * @param filePath The XML file path.
     */
    public static void removeNodeByAttribute(String tagName, String attrName, String attrValue, String filePath) {
        try {
            Document doc = getDocument(filePath);
            NodeList nodes = doc.getElementsByTagName(tagName);
            boolean found = false;

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                NodeList childNodes = element.getElementsByTagName(attrName);
                if (childNodes.getLength() > 0 && childNodes.item(0).getTextContent().equals(attrValue)) {
                    element.getParentNode().removeChild(element);
                    found = true;
                    break;
                }
            }

            if (found) {
                writeXmlFile(doc, new File(filePath));
                System.out.println("✅ " + tagName + " successfully removed from XML!");
            } else {
                System.out.println("❌ No matching " + tagName + " found for removal.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves the XML document from the specified file path.
     * @param filePath The XML file path.
     * @return The Document object representing the XML file.
     * @throws Exception If an error occurs while parsing the XML file.
     */
    private static Document getDocument(String filePath) throws Exception {
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc;

        if (xmlFile.exists() && xmlFile.length() > 0) {
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            removeWhitespace(doc.getDocumentElement());
        } else {
            doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Customers_Events");
            doc.appendChild(rootElement);
        }
        return doc;
    }
    /**
     * Creates an XML element for a customer.
     * @param doc The XML document.
     * @param cus The customer object.
     * @param count The unique identifier for the customer.
     * @return The created XML element.
     */
    private static Element createUserElement(Document doc, Customer cus, int count) {
        Element customerElement = doc.createElement("Customer" + count);
        addElementWithText(doc, customerElement, "Event_ID", cus.getEventID());
        addElementWithText(doc, customerElement, "Last_Name", cus.getLastName());
        addElementWithText(doc, customerElement, "First_Name", cus.getFirstName());
        addElementWithText(doc, customerElement, "Contact_Number", String.valueOf(cus.getContactNo()));
        addElementWithText(doc, customerElement, "Email", cus.getEmail());
        addElementWithText(doc, customerElement, "Seat", String.valueOf(cus.getSeatNo()));
        return customerElement;
    }
    /**
     * Creates an XML element representing an Event object.
     *
     * @param doc   The XML document to which the element belongs.
     * @param event The event object to be converted into an XML element.
     * @return The created XML element representing the event.
     */
    private static Element createEventElement(Document doc, Event event) {
        Element eventElement = doc.createElement("Event");
        addElementWithText(doc, eventElement, "Event_ID", event.getEventID());
        addElementWithText(doc, eventElement, "Event_Name", event.getEventName());
        addElementWithText(doc, eventElement, "Start_Time", event.getStartTime());
        addElementWithText(doc, eventElement, "End_Time", event.getEndTime());
        addElementWithText(doc, eventElement, "Event_Day", String.valueOf(event.getEvDay()));
        addElementWithText(doc, eventElement, "Event_Month", String.valueOf(event.getEvMonth()));
        addElementWithText(doc, eventElement, "Event_Description", event.getDescription());
        return eventElement;
    }
    /**
     * Adds an XML element with text content to a parent element.
     *
     * @param doc    The XML document to which the element belongs.
     * @param parent The parent element to which the new element will be appended.
     * @param tag    The tag name of the new element.
     * @param text   The text content of the new element.
     */
    private static void addElementWithText(Document doc, Element parent, String tag, String text) {
        Element element = doc.createElement(tag);
        element.appendChild(doc.createTextNode(text));
        parent.appendChild(element);
    }
    /**
     * Recursively removes whitespace-only text nodes from an XML document.
     *
     * @param node The root node from which whitespace nodes will be removed.
     */
    private static void removeWhitespace(Node node) {
        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                String text = child.getTextContent().trim();
                if (text.isEmpty()) {
                    node.removeChild(child);
                }
            } else {
                removeWhitespace(child);
            }
        }
    }
    /**
     * Writes an XML document to a file with indentation for readability.
     *
     * @param doc  The XML document to be written.
     * @param file The file to which the XML document will be written.
     * @throws TransformerException If an error occurs during the transformation process.
     */
    private static void writeXmlFile(Document doc, File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

}