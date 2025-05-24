/*
Name: Mayo, Kenneth Charles P.
Date: August 16, 2024
Exercise Name and Number: LabExercise1

Problem:
 Create an executable class that implements/illustrates the Selection Sort BroCodeDSA.Algorithm
   - The class should have a method that implements the Selection Sort BroCodeDSA.Algorithm.
       The method should have a 1-dimensional array of string as formal parameter
   - The class should have a method that displays elements of a 1-dimensional array
   - In the code ( e.g. in the main method), declare a one-dimensional array of strings.
       For this exercise, you may assign the elements of the array upon declaring the array
       (The array should have at least 10 elements)
   - Let the program show the elements of the array before the sorting happens
   - Let the program show the elements of the array after sorting
   - Show that sample output of the program through multiple line comments in the source file
    ( i.e. After running the programming, copy and paste the output within a comment section of the source code).

Program BroCodeDSA.Algorithm:
1.First instantiate the array first but in my case, I made a for loop for each element so
  that the user can freely enter their desired names. After that, I made the for loop max
  of 10 so each element will be put in the array. After that, I show the element unsorted
  before I sort it, I simply call the method of showElement and put the array in that method.

2.After that the string was shown, the next method will be called which is the sortArray method
  which in this case, the given String will be sorted in alphabetically by using the Selection Sort
  BroCodeDSA.Algorithm.
*/

package prelimExercises;

import java.util.Scanner; //I Imported the scanner so the user can input for their desired names
public class LabExercise1 {
    public static void main(String[] args) { //main method

        Scanner scan = new Scanner(System.in); //instantiation of the scanner class

        String[] arrayOfNames = new String[10]; //instantiation of the array but this time I made the length of 10

        //For loop for iterating each name inside the array
        for (int i = 0; i < 10; i++) {
            System.out.print("Enter a Name here : "); //Prompt the user to enter
            String names = scan.nextLine();

            arrayOfNames[i] = names; //the "i" define the index of the array
        }
        System.out.println();

        System.out.println("Unsorted List of Names : ");
        showElements(arrayOfNames); //It will first show the unsorted one

        sortArray(arrayOfNames); //It will sort inside the method so after
        // it will sort inside the method it will automatically update
        // the array since we instantiated before even it's not static

        System.out.println("Sorted List of Names : ");
        showElements(arrayOfNames); //It will show the array after it sorted

    }

    /**
     Sorts an array of String using the Selection Sort BroCodeDSA.Algorithm.
     BroCodeDSA.Algorithm:
     1 − Set Min_Index to 0
     2 − Search for the smallest element in the array
     3 − Swap with value with the element at the Min_Index
     4 − Increment Min_Index to point to next element
     5 − Repeat until the complete array is sorted
     */
    public static void sortArray(String[] array) { //sorting array method

        // One by one move the boundary of the unsorted part
        for (int i = 0; i < array.length - 1; i++) {
            // Find the minimum element in the unsorted part
            int  Min_Index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[Min_Index]) < 0) {
                    Min_Index = j; // Update Min_Index if a smaller element alphabetically is found
                }
            }

            // Swap the found minimum element with the first unsorted element
            String temp = array[Min_Index];
            array[Min_Index] = array[i];
            array[i] = temp;
        }
    }

    /**
     Displays the elements of an array of strings
     BroCodeDSA.Algorithm:
     Just Simply use the for each loop to iterate each
     element in the array
     */
    public static void showElements(String[] array){ //showing elements method

        for (String ar : array) { //For Each Loop to iterate each element inside of array
            System.out.print(ar + ", "); //Show in the console one by one
        }
        System.out.println();
    }
}
