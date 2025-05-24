package prelimExercises;
/*
Name: MAYO, Kenneth Charles P.
Date: August 20, 2024
Exercise Name and Number: prelim.exercises.LabExercise2

Be reminded that our course (Data Structures) covers analysis of algorithms. Hence, we must be familiar with some fundamental algorithms.  Linear Search  is one of the fundamental algorithms in computer programming.

Problem:
1. Create a project using the IntelliJIDEA IDE
2. Let the project contain a package called prelim.exercises
3. Include in the package the class that you have created for the previous exercise ( The class that applied the Selection Sort BroCodeDSA.Algorithm)
3. Create another executable class that implements/illustrates the Linear Search BroCodeDSA.Algorithm.  Let this class be in the same package (prelim.exercises)
   - The class should have a method that implements the  Linear Search BroCodeDSA.Algorithm. The method should have a 1-dimensional array of string* and a search key as formal parameters.
   - The class should have a method that displays elements of a 1-dimensional array
   - In the code( e.g. in the main method or run method), declare a one-dimensional array of strings. For this exercise, you may assign the elements of the array upon declaring the array (The array should have at least 10 elements)
   - Let the program show the elements of the array before the searching happens
   - Let the program search for a specific element from the array
   - Show that sample output of the program through multiple line comments in the source file ( i.e. After running the programming, copy and paste the output within a comment section of the source code).

*You may create an array of objects of a user-defined class.  Hence, you will search from the array an object with a specified attribute.

Program BroCodeDSA.Algorithm:
1. Instantiate the array first, and the LabExercise1 for the methods so that we can still use the method we made before
2. Let the user enter a List of String using the for-loop
3. And then the menu will be shown for the user to choose what option he want.
4. Option one for Sorting the List, we use the method we made last exercise by instantiating the class and the method
5. Option two is the Searching the algorithm, we used the linear-search algorithm.

EXAMPLE OF OUTPUT :

Enter a Name here : Kenneth
Enter a Name here : Charles
Enter a Name here : Patio
Enter a Name here : Mayo
Enter a Name here : Micheal
Enter a Name here : Vincent
Enter a Name here : Jared
Enter a Name here : Facun
Enter a Name here : Frenzel
Enter a Name here : Abigail

Unsorted List of Names :
Kenneth, Charles, Patio, Mayo, Micheal, Vincent, Jared, Facun, Frenzel, Abigail,

Please choose a number
1. Sort the List
2. Search a position
3. Exit the Program
Enter here :2

enter a key that will find in the list :
Frenzel
Word found at position 9
The Size of the List : 10

*/
import java.util.Scanner; //Importing the scanner
    public class LabExercise2 {
        public static void main(String[] args) { //Main Method
            Scanner scan = new Scanner(System.in); //Instantiation of the scanner
            String[] arrayOfNames = new String[10]; //Instantiation of the array with setting the values to 10
            new LabExercise1(); //Instantiation of the LabExercise1 class so we can still use the method
            for (int i = 0; i < 10; i++) { //For Loop for the entering the names for the List
                System.out.print("Enter a Name here : "); //Prompt the user to enter
                String names = scan.nextLine();

                arrayOfNames[i] = names; //Inserting the user input into the array
            }
            int option = 0;
            System.out.println("Unsorted List of Names : ");
            LabExercise1.showElements(arrayOfNames); //Calling the method in the LabExercise1 to show the elements
            System.out.println();

            while (option > 0 || option < 3) { //While Loop for, if the user enter less than 0 or more than 3 it
                // will still loop unless you entered 3
                System.out.print("""
                        Please choose a number
                        1. Sort the List
                        2. Search a position
                        3. Exit the Program
                        Enter here :""");
                option = Integer.parseInt(scan.nextLine());
                System.out.println();
                switch (option) { //Switch cases for the option
                    case 1:
                        System.out.println("Sorted List : ");
                        LabExercise1.sortArray(arrayOfNames); //Called the method in the LabExercise1 to sort Array with selection sort
                        LabExercise1.showElements(arrayOfNames); //Calling the method in the LabExercise1 to show the elements
                        System.out.println();
                        break;
                    case 2:
                        arraySearch(arrayOfNames); //Called the arraySearch Method that used Linear Search BroCodeDSA.Algorithm
                        System.out.println();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
                System.out.println("The Size of the List : " + arrayOfNames.length); //Showing the size of the list

            }
        }

        /**
         * Method that implements the  Linear Search BroCodeDSA.Algorithm.
         * BroCodeDSA.Algorithm:
         * 1. Let the user enter a string for the key
         * 2. After make a for-each loop for the array
         * 3. So after that, it will check if the key is same with the data within the array
         * 4. Then if it's same it will return 1 and not it will return -1
         * 5. But in this case, we're using string so instead of returning it. It will just
         * check using the if else statement.
         */
        public static void arraySearch(String[] array) {
            Scanner scan = new Scanner(System.in);
            System.out.println("enter a key that will find in the list : ");
            String key = scan.nextLine();

            int checker = 0; //Checker for, if there's a element inside it will instantiate as 1 inside the for loop
            int i;
            for (i = 0; i < array.length; i++) { //For Loop for iterating inside the array
                if (array[i].equals(key)) { //If statement for if key is same with the array that iterated using the for loop
                    checker = 1; //Then the checker will be 1
                    break;
                }
            }
            //If-else statement so if the word is found then it will print the index of it and if not then, it will tell
            //the word is not found in the array.
            if (checker == 1) {
                System.out.print("Word found at position " + (i + 1));
            } else {
                System.out.println("Word not found.");
            }
        }
    }