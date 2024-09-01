package prelim;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class TesterFixedArrayMain {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        MyFixedSizeArrayList<Car> fixedList = new MyFixedSizeArrayList<>();
        boolean isContinue = false;
        int option = 0;
        try {
            fixedList.insert(new Car("Porsche", "911 GT3 RS", 2003));
            fixedList.insert(new Car("lamborghini", "Huracan", 2014));
            fixedList.insert(new Car("Toyota", "Camry", 2020));
            fixedList.insert(new Car("Honda", "Accord", 2022));
//                fixedList.insert(new Car("Lamborghini", "Aventador SVJ", 2021));

            while (!isContinue || option == 7) {
                System.out.println();
                System.out.println("""
                        ===================================
                        Welcome to the Car Show
                        Please Choose what you like to do
                        There's already 5 car in the LIST
                        1. View the Car List
                        2. Insert a Car in the Car List
                        3. View the size of the Car List
                        4. View the one Car in the List
                        5. Delete a Car in the Car List
                        6. Search the position of a Car
                           in the Car List
                        7. Exit the Car Show
                        ===================================""");
                System.out.print("Enter here : ");
                option = Integer.parseInt(scan.nextLine());
                while (option < 1 || option > 7) {
                    System.out.print("Input Exceeded, Try again (1 to 7): ");
                    option = Integer.parseInt(scan.nextLine());
                }

                switch (option) {
                    case 1:
                        System.out.println("Car List : ");
                        System.out.println(fixedList);
                        break;
                    case 2:
                        System.out.print("Enter the Brand of a Car : ");
                        String brandCar = scan.nextLine();

                        System.out.print("Enter what model is the Car : ");
                        String modelCar = scan.nextLine();

                        System.out.print("Enter what year is the Car : ");
                        int yearCar = Integer.parseInt(scan.nextLine());

                        fixedList.insert(new Car(brandCar, modelCar, yearCar));
                        System.out.println("Car Successfully Added");
                        break;
                    case 3:
                        System.out.println("Size of the Car List : ");
                        System.out.println(fixedList.getSize());
                        break;
                    case 4:
                        System.out.print("Enter the Model of the Car : ");
                        String model = scan.nextLine();
                        Car myCar = new Car();
                        myCar.setModel(model);

                        Car foundCar = fixedList.getElement(myCar);
                        System.out.println(foundCar);
                        break;
                    case 5:
                        System.out.print("Enter what model of Car will be deleting : ");
                        String model2 = scan.nextLine();
                        Car myCar2 = new Car();
                        myCar2.setModel(model2);
                        System.out.println(fixedList.delete(myCar2));
                        System.out.println(fixedList);
                        break;
                    case 7:
                        System.exit(0);
                        isContinue = true;

                }
            }

        } catch (ListOverFlowException lofe) {
            System.out.println("Overflow Array : " + lofe);
        } catch (NoSuchElementException nsee) {
            System.out.println(nsee.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.print("You entered a invalid input, " +
                    "to input a number 1 to 7 only! : ");
        }
    }
}