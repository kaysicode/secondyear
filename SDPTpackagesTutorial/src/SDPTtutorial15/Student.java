package SDPTtutorial15;

public class Student {
    private String name, hobby;
    private int age, gradeLVL;

    public Student(String name, String hobby, int age, int gradeLVL) {
        this.name = name;
        this.hobby = hobby;
        this.age = age;
        this.gradeLVL = gradeLVL;

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
