package prelimExercises;

import java.util.ArrayList;

public class tryLangMuna {
    public static void main(String[] args) {
        ArrayList<Car> arrayList = new ArrayList<>();

        arrayList.add(new Car("Toyota", "M3", 2019));
        arrayList.add(new Car("Novia", "Versoin19", 2020));
        arrayList.add(new Car("Porsche", "911", 2024));

        System.out.println(arrayList);
        
    }
}
