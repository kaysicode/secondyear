package exceptionHandling;
import java.util.Scanner;
public class exceptionHandlingPractice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int checkNum = -3;

        if(checkNum < 1) {
            throw new ArithmeticException(checkNum + "cannot be less than to 1");
        } else {
            System.out.println("Square of " + checkNum + " is " + (checkNum*checkNum));
        }


    }
}
