package selftaughtpractice.sortingStudents;

public class Student {

    //data members
    private String lastName;
    private String firstName;
    private String midIntial;
    private int age;
    private String sex;
    private String birthday;
    private double grades;

    //constructor

    public Student(String lastName, String firstName, String midInital, int age, String sex, String birthday, double grades){
        this.lastName = lastName;
        this.firstName = firstName;
        this.midIntial= midInital;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        this.grades = grades;
    }

    public Student(String[] data) {
        this.lastName = data[0];
        this.firstName = data[1];
        this.midIntial= data[2];
        this.age = Integer.parseInt(data[3]);
        this.sex = data[4];
        this.birthday = data[5];
        this.grades = Double.parseDouble(data[6]);
    }

    //setters and getters3

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMidIntial() {
        return midIntial;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getGrades() {
        return grades;
    }


    //methods
}
