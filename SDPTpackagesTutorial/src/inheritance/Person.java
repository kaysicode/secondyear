package inheritance;

public class Person {
    public String name, sex;
    public int age;

    public Person(){

    }

    public Person(String name, String sex, int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public void displayResult(){
        System.out.println("My Name is " + name);
        System.out.println("My Sex is " + sex);
        System.out.println("My age is " + age);
    }

}
