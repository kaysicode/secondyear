package SimpleProjectExercise;

import java.util.Scanner;
import java.util.Stack;

public class PostfixConversion {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String result = "";

        Stack<Character> stackOperators = new Stack<>();

        System.out.print("Enter a infix expression : ");
        String infixExpression = scan.nextLine();

        for (int i = 0; i < infixExpression.length(); i++) {
            char symbol = infixExpression.charAt(i);

            if (Character.isLetterOrDigit(symbol)) {
                result += symbol;
            } else if (symbol == '(') {
                stackOperators.push(symbol);
            } else if (symbol == ')') {
                while (!stackOperators.isEmpty() && stackOperators.peek() != '(') {
                    result += stackOperators.pop();
                }
                if (!stackOperators.isEmpty() && stackOperators.peek() == '(') {
                    stackOperators.pop();
                }
            } else {
                while (!stackOperators.isEmpty() && precedence(stackOperators.peek()) > precedence(symbol)) {
                    result += stackOperators.pop();
                }
                stackOperators.push(symbol);
            }
        }

        while (!stackOperators.isEmpty()) {
            result += stackOperators.pop();
        }

        System.out.println("The result is : " + result);

    }

    private static int precedence(char symbol) {
        return switch (symbol) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
}
