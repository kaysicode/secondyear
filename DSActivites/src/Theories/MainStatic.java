package Theories;

public class MainStatic {
    public static void main(String[] args) {

        Person person = new Person();


        person.name = "Kenneth";
        person.age = 12;


        person.sayHi();

        Person.sayHi2("Charles", 13);


    }
}
