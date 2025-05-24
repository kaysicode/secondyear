import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class DOMFileWriter {
    public static void main(String[] args) {
        Document document;
        document = createDocument();
        writeDOMToFile(document.getFirstChild(), "res/departments.xml");

        document = retireFaculty(document, "Dina Matuto");
        writeDOMToFile(document.getFirstChild(), "res/updated_departments.xml");
    }

    private static Document retireFaculty(Document document, String emp) {
        NodeList nodes = document.getElementsByTagName("name");

        // delete occurrences of employee using Node class
        for (int i = 0; i < nodes.getLength(); i++) {
            Node currentNode = nodes.item(i);
            String empName = currentNode.getTextContent();
            if (empName.equalsIgnoreCase(emp)) {
                Node employee = currentNode.getParentNode();
                Node department = employee.getParentNode();
                department.removeChild(employee);
                i--;
            }
        }

        // remove department that doesn't have any employee using Element class
        Node root = document.getFirstChild();
        NodeList departments = document.getFirstChild().getChildNodes();
        for (int i = 0; i < departments.getLength(); i++) {
            Node department = departments.item(i);
            NodeList employees = department.getChildNodes();
            if (employees.getLength() == 0) {
                root.removeChild(department);
                i--;
            }
        }
        return document;
    }

    private static void writeDOMToFile(Node node, String fileName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(node);

            PrintWriter fileWriter = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(fileWriter);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Document createDocument() {
        Document document = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();

            Element root = document.createElement("departments");
            document.appendChild(root);

            Element department = document.createElement("IT_MMA");
            // populate employee(s) of IT/MMA department
            assignFacultyToDepartment(department, document, "full-time", "10", "Pedro Penduko");
            assignFacultyToDepartment(department, document, "contractual", "100", "Juan Masipag");
            assignFacultyToDepartment(department, document, "full-time", "5", "Dina Matuto");
            root.appendChild(department);

            department = document.createElement("CS_CAD");
            // populate employee(s) of CS/CAD department
            assignFacultyToDepartment(department, document, "full-time", "5", "Dina Matuto");
            root.appendChild(department);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return document;
        }
    }

    private static void assignFacultyToDepartment(Element department, Document doc, String status, String id, String name) {
        Element employee = doc.createElement("faculty");
        employee.setAttribute("status",status);
        Element empID = doc.createElement("id");
        Text txtData = doc.createTextNode(id);
        empID.appendChild(txtData);
        Element empName = doc.createElement("name");
        txtData = doc.createTextNode(name);
        empName.appendChild(txtData);

        employee.appendChild(empID);
        employee.appendChild(empName);
        department.appendChild(employee);
    }
}
