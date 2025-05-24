import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMParserDemo {

    public static void main(String[] args) {
        try {
            File inputFile = new File("res/data.xml");
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputFile);

            document.getDocumentElement().normalize();
            //System.out.println(document.getDocumentElement().getNodeName());

            NodeList nodes = document.getElementsByTagName("student");
            printStudents(nodes);
            nodes = document.getElementsByTagName("employee");
            printEmployees(nodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printEmployees(NodeList nodes) {
        System.out.println("Employee List:");
        System.out.println("----------------------------");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Element element = (Element) node;
            String empType = element.getAttribute("type");
            String id = element
                    .getElementsByTagName("id")
                    .item(0)
                    .getTextContent();
            String name = element
                    .getElementsByTagName("name")
                    .item(0)
                    .getTextContent();
            String pos = element
                    .getElementsByTagName("position")
                    .item(0)
                    .getTextContent();
            String dept = element
                    .getElementsByTagName("department")
                    .item(0)
                    .getTextContent();

            System.out.println("Employee information:");
            Employee employee = new Employee(id, empType, name, pos, dept);
            System.out.println(employee);
            System.out.println(i == (nodes.getLength() - 1) ? "----------------------------": "");
        }
        System.out.println();
    }

    private static void printStudents(NodeList nodes) {
        System.out.println("Student List:");
        System.out.println("----------------------------");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Element element = (Element) node;
            String id = element
                    .getElementsByTagName("id")
                    .item(0)
                    .getTextContent();
            String name = element
                    .getElementsByTagName("name")
                    .item(0)
                    .getTextContent();
            String course = element
                    .getElementsByTagName("course")
                    .item(0)
                    .getTextContent();
            int yr = Integer.parseInt(
                    element.getElementsByTagName("year")
                    .item(0)
                    .getTextContent());

            System.out.println("Student information:");
            Student student = new Student(id, name, course, yr);
            System.out.println(student);
            NodeList phones = element.getElementsByTagName("phone");
            if (phones.getLength() > 0) {
                System.out.println("Phone number/s:");
                for (int x = 0; x < phones.getLength(); x++) {
                    Node phone = phones.item(x);
                    System.out.println(phone.getTextContent());
                }
            }
            System.out.println(i == (nodes.getLength() - 1) ? "----------------------------": "");
        }
        System.out.println();
    }
}