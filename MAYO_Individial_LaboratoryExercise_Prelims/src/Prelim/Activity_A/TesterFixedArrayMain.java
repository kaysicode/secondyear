package Prelim.Activity_A;

import Prelim.Activity_A.Car;
import Prelim.Activity_A.ListOverFlowException;
import Prelim.Activity_A.MyFixedSizeArrayList;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class TesterFixedArrayMain {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) { // Main method to executable the MyFixedArrayList

        // Instantiation of MyFixedArrayList with the datatype of a object Car
        MyFixedSizeArrayList<Car> fixedList = new MyFixedSizeArrayList<>();
        boolean isContinue = false;
        int option = 0;
        try {
            // Insert various Cars objects into the fixedList
            fixedList.insert(new Car("Porsche", "911 GT3 RS", 2003));
            fixedList.insert(new Car("lamborghini", "Huracan", 2014));
            fixedList.insert(new Car("Toyota", "Camry", 2020));
            fixedList.insert(new Car("Honda", "Accord", 2022));

            // Menu, it will loop until you enter 7
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

                //Switch-Case Statement for the menu
                switch (option) {
                    case 1:
                        // Print all elements in the fixedList
                        System.out.println("Car List : ");
                        System.out.println(fixedList);
                        break;
                    case 2:
                        // Add a new car in the List
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
                        // Print the size of the list
                        System.out.println("Size of the Car List : ");
                        System.out.println(fixedList.getSize());
                        break;
                    case 4:
                        //Get the element in the list by using the model name of the car
                        System.out.print("Enter the Model of the Car : ");
                        String model = scan.nextLine();
                        Car myCar = new Car();
                        myCar.setModel(model);

                        Car foundCar = fixedList.getElement(myCar);
                        System.out.println(foundCar);
                        break;
                    case 5:
                        //Delete the element in the list
                        System.out.print("Enter what model of Car will be deleting : ");
                        String model2 = scan.nextLine();
                        Car myCar2 = new Car();
                        myCar2.setModel(model2);
                        System.out.println(fixedList.delete(myCar2));
                        System.out.println(fixedList);
                        break;
                    case 6:
                        // Search the index of the element in the list by using the Model Name of the Car
                        System.out.print("Enter what model of Car will be searching : ");
                        String search = scan.nextLine();
                        Car searchCar = new Car();
                        searchCar.setModel(search);
                        System.out.println("Index : " + fixedList.search(searchCar));
                        break;
                    case 7:
                        //Exit the Program
                        System.exit(0);
                        isContinue = true;

                }
            }

        } catch (ListOverFlowException lofe) { // Catch clause for the ListOVerFlowException to catch if the list is greater than the 10
            lofe.printStackTrace();
        } catch (NoSuchElementException nsee) { // Exception so if there's no such element in the array, this exception will be thrown
            System.out.println(nsee.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.print("You entered a invalid input, " +
                    "to input a number 1 to 7 only! : ");
        }
    }
}