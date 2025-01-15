package inheritance;

public class Main {
    public static void main(String[] args){


        String name = "Charles";
        String sex = "Male";
        int age = 20;

        String name2 = "Kenneth";
        String sex2 = "Male";
        int age2 = 19;

        Person p = new Person(name, sex, age);
        Toddler t = new Toddler(name2, sex2, age2, "Peek-a-boo");
        Kid k = new Kid("James Mark", "Male", 13, "Mobile Legends", "Pantintero");


        t.drink();
    }
}
