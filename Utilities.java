package selftaughtpractice.sortingStudents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Utilities {
    public void sortLastName(ArrayList<Student> str, String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String x;
            String [] splitOn;
            while ((x = br.readLine()) != null) {
                splitOn = x.split(",");
                str.add(new Student(splitOn));
            }

            List<Student> result = str.stream()
                    .sorted(Comparator.comparing(Student::getLastName))
                    .toList();

            System.out.println(result);

        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}
