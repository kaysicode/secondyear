package abstractionOne;

public class Main {
    public static void main(String[] args) {

        //Abstract Class
        //Animal a = new Animal();


        //Concrete Class
        Animal c = new Cat();
        Animal d = new Dog();

        c.makeSound();
        d.makeSound();

    }
}
