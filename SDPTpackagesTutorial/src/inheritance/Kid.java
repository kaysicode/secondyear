package inheritance;

public class Kid extends Toddler{
    private String hobby;

    public Kid(String name, String sex, int age, String favoriteGame, String hobby){
        super(name, sex, age, favoriteGame);
        this.hobby = hobby;
    }

    public void sayHobby(){
        System.out.println("I am " + name + "and my favorite hobby is " + hobby);
    }
}
