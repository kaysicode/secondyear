package inheritance;

public class Toddler extends Person{
    public String favoriteGame;
    public Toddler(String name, String sex, int age, String favoriteGame){
        super(name, sex, age);
        this.favoriteGame = favoriteGame;
    }
    public void drink(){
        System.out.println(name + " is Drinking Milk");
    }

    public void displayResult(){
        super.displayResult();
        System.out.println("Favorite game is " + favoriteGame);
    }
}
