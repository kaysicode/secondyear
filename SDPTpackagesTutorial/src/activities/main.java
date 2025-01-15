package activities;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your fist name : ");
        String fName = scan.nextLine();

        System.out.print("Enter your last name : ");
        String lName = scan.nextLine();


        System.out.print("Enter your  sex : ");
        String sex = scan.nextLine();

        System.out.print("Enter your  Students ID : ");
        int sID = scan.nextInt();

        System.out.print("Enter your  age : ");
        int age = scan.nextInt();

        Students s1 = new Students(fName, lName, sex, sID, age);
    }
}
