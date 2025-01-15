package activities;

public class Students {

    String firstName;
    String lastName;
    String sex;
    int studentID;
    int age;

    Students(String firstName, String lastName, String sex, int studentID, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.studentID = studentID;
        this.age = age;

        System.out.println("You're " + firstName + " " + lastName + "\n" + "You're an " + sex + "\n" +  "that's " + age + " Years Old." + "\n" + "Your ID no. is " + studentID);

    }
}
