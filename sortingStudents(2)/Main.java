package selftaughtpractice.sortingStudents;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Student> studentList = new ArrayList<>();
    static String fileName = "src/selftaughtpractice/sortingStudents/studentData";

    public static void main(String[] args) {

        showMenu();
    }
    public static void showMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.print("""
                This is Student Sorts,
                whereas we sort student based
                on your options, first use to
                check or add a students.
                1. Add a student
                2, Sort a student
                Enter here :""");

        int option;
        boolean inputWord = false;

        while (!inputWord) {
            try {
                option = Integer.parseInt(scan.nextLine());
                while (option < 1 || option > 2) {
                    System.out.print("Input Exceeded, Try again (1 or 2): ");
                    option = Integer.parseInt(scan.nextLine());
                }
                inputWord = true;
                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        sortingMenu();
                        break;
                }
            } catch (NumberFormatException nfe) {
                System.out.print("You entered a invalid input, " +
                        "to input a number 1 or 2 only! : ");
            }
        }
    }
    public static void sortingMenu() {
        Utilities utilities = new Utilities();
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                Please enter a number of which
                you like to sort the student
                ==================================
                1. Sort by Last Name
                2. Sort by First Name
                3. Sort by Birthday
                4. Sort by grades
                5. Filter who is legals
                6. Filter who is minors
                7, Filter by sex
                8. Get the student info
                9. Exit
                """);
        int option1;
        boolean isTrue = false;

        while (!isTrue) {
            try {
                System.out.print("Enter a number here : ");
                option1 = Integer.parseInt(scan.nextLine());

                while (option1 < 0 || option1 > 9) {
                    System.out.print("Invalid Input, try again");
                    option1 = Integer.parseInt(scan.nextLine());
                }
                isTrue = true;
                switch (option1) {
                    case 1:
                        utilities.sortLastName(studentList, fileName);
                        break;
                    case 2:

                }
            } catch (NumberFormatException nfe) {
                System.out.print("You entered a invalid input, " +
                        "to input a number 1 or 2 only! : ");
            }
        }
    }
    public static void addStudent() {
        int another = 0;

        do {
            studentList = new ArrayList<>();
            Scanner scan = new Scanner(System.in);

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));

                System.out.print("Enter a the student's last name : ");
                String lastName = scan.nextLine();

                System.out.print("Enter a the student's first name : ");
                String firstName = scan.nextLine();

                System.out.print("Enter a the student's middle initial : ");
                String midInitial = scan.nextLine();

                System.out.print("Enter a the student's age : ");
                int age = Integer.parseInt(scan.nextLine());

                System.out.print("Enter a the student's sex : ");
                String sex = scan.nextLine();

                System.out.print("Enter a the student's birthday (e.g : February20 ) : ");
                String birthday = scan.nextLine();

                System.out.print("Enter a the student's grades : ");
                double grades = Double.parseDouble(scan.nextLine());

                String upperLastName = stringUpperCase(lastName);
                String upperFirstName = stringUpperCase(firstName);
                String upperMidInit = stringUpperCase(midInitial);
                String upperBirth = stringUpperCase(birthday);


                bw.write("\n" + upperLastName + "," + upperFirstName + "," + upperMidInit + "," + age + "," + sex + "," + upperBirth + "," + grades);
                bw.close();

                System.out.println("Student is successfully added");

                System.out.print("""
                Do you want to still to add another student?
                1. Yes
                2. No
                Enter here :""");
                another = scan.nextInt();

                studentList.add(new Student(lastName, firstName, midInitial, age, sex, birthday, grades));

            } catch (IOException ioe) {
                System.out.println("There's wrong in file, can't write it");
            } catch (NumberFormatException nfe) {
                System.out.println("You enter a invalid, try again");
            }
        } while (another != 2);

        showMenu();
    }

    public static String stringUpperCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
