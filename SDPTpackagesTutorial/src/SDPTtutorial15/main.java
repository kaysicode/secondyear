package SDPTtutorial15;
import java.util.Scanner;
public class main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name ");
        String nameS = scanner.nextLine();

        System.out.println("What's your hobby : ");
        String hobbyA = scanner.nextLine();

        System.out.println("Enter your age : ");
        int ageS = scanner.nextInt();

        System.out.println("What's your grade level : ");
        int gradeLVLs = scanner.nextInt();

        Student studentA = new Student(nameS, hobbyA, ageS, gradeLVLs);
        studentA.setName("Kenneth");
        System.out.println(studentA.getName());

    }
}
