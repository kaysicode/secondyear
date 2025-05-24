package pexer3;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import java.io.*;
import java.net.Socket;

/**
 * The PreExercise3Client class reads mathematical expressions from an XML file,
 * sends them to the server for evaluation, and prints the results.
 * The client terminates after processing all expressions.
 */
public class PreExercise3Client {
    public static void main(String[] args) {
        String serverHost = "127.0.0.1";
        int port = 3000;
        String filePath = "res/exer3.xml";

        try (Socket socket = new Socket(serverHost, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server.");

            // Read expressions from the XML file and send to the server
            processXMLFile(filePath, writer, reader);

            // Send termination signal to server
            writer.println("bye");
            System.out.println("Client disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the XML file and extracts mathematical expressions.
     * Each expression is sent to the server, and the server response is printed.
     * @param filePath The path of the XML file containing expressions.
     * @param writer Output stream to send data to the server.
     * @param reader Input stream to receive results from the server.
     */
    private static void processXMLFile(String filePath, PrintWriter writer, BufferedReader reader) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList expressionList = document.getElementsByTagName("expression");

            for (int i = 0; i < expressionList.getLength(); i++) {
                Node node = expressionList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String operand1 = element.getElementsByTagName("operand1").item(0).getTextContent();
                    String operator = element.getElementsByTagName("operator").item(0).getTextContent();
                    String operand2 = element.getElementsByTagName("operand2").item(0).getTextContent();

                    String expression = operand1 + " " + operator + " " + operand2;
                    writer.println(expression); // Send expression to server
                    System.out.println(reader.readLine()); // Print server response
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
