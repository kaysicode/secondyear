package Theories;

public class Person {
    protected String name;
    protected int age;

    public Person() {

    }

    public static void sayHi2(String name, int age) {

        System.out.println("Static:");

        System.out.println("My name is" + name +" and my age is " + age);

    }

    public void sayHi() {
        System.out.println("Non-Static:");

        System.out.println("My name is " + name + " and my age is " + age);
    }
}
