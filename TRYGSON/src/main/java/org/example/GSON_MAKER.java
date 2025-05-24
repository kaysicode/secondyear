package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

record Person(
   String name,
   int age
) {}


public class GSON_MAKER {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Person person = new Person("John Doe", 25);

        try (FileWriter write = new FileWriter("person.json")) {
            gson.toJson(person, write);
            System.out.println("OK NA IDOL");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try(FileReader reader = new FileReader("person.json")) {
            Person p = gson.fromJson(reader, Person.class);
            System.out.println(p.name() + " \n" + p.age());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
