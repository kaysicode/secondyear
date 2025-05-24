package pexer3;

import java.io.*;
import java.net.*;
import java.util.regex.Pattern;

/**
 * The PreExercise3Server class implements a server that listens for client connections,
 * processes mathematical expressions received from the client, and returns the computed results.
 * The server handles multiple clients using threads and terminates when it receives the "bye" message.
 */
public class PreExercise3Server {
    public static void main(String[] args) {
        int port = 3000;
        System.out.println("Server is running on port " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle client communication in a separate thread
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Handles communication with a connected client.
 * It reads expressions, processes them, and sends the computed results back.
 */
class ClientHandler implements Runnable {
    private Socket clientSocket;

    /**
     * Constructor that initializes the client socket.
     * @param socket The socket representing the connection to the client.
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String input;
            while ((input = reader.readLine()) != null) {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Client says bye!");
                    System.out.println("Client disconnected.");
                    break;
                }


                // Process the expression and send the result
                String result = evaluateExpression(input);
                writer.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Evaluates a mathematical expression received from the client.
     * @param expression The input expression in the format "operand1 operator operand2".
     * @return The computed result or an error message if the expression is invalid.
     */
    private String evaluateExpression(String expression) {
        String[] parts = expression.split(" ");
        if (parts.length != 3) return expression + " = Invalid expression";

        String operand1 = parts[0], operator = parts[1], operand2 = parts[2];

        // Validate if operands are numbers
        if (!isValidNumber(operand1) || !isValidNumber(operand2)) {
            return expression + " = Invalid expression";
        }

        double num1 = Double.parseDouble(operand1);
        double num2 = Double.parseDouble(operand2);
        double result;

        switch (operator) {
            case "^": result = Math.pow(num1, num2); break;
            case "*": result = num1 * num2; break;
            case "/": result = (num2 != 0) ? num1 / num2 : Double.NaN; break;
            case "%": result = num1 % num2; break;
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            default: return expression + " = Invalid expression";
        }

        return String.format("%s %s %s = %.2f", operand1, operator, operand2, result);
    }

    /**
     * Checks if a given string is a valid number (integer or decimal).
     * @param str The input string to validate.
     * @return True if the string is a valid number, false otherwise.
     */
    private boolean isValidNumber(String str) {
        return Pattern.matches("-?\\d+(\\.\\d+)?", str);
    }
}
