/**
 NAME: MAYO, Kenneth Charles P.
 DATE: October 15, 2024
 CLASS CODE: 9458B
 One application of the Stack Data Structure is the problem of determining whether a string is a
 palindrome or not.
 Problem:
 Write a program that will determine if a string that is entered at run time is a palindrome.
 Some examples of palindromes are:
 abbbdcdbbba
 mirrorrorrim
 nasabayabasan
 Specifications.
 -Your program should utilize a Stack data structure
 -Although you can easily solve the problem by using methods of the string class(i.e. There is
 method in the String class for getting the reversed form of a string), your program should
 assume that there is no such method. Use only the method to access a character in the string
 (charAt method) as well as the method to determine the length of the string(length method). Let
 this exercise prepare you to solve the problem using a bare programming language platform
 (i.e. a programming language that has very limited library of methods/functions)

 Present the sample output below:
 ------------------------------------

 // If the word is palindrome
 This application helps you evaluate if a string is a palindrome or not
 Please enter the string: abbbdcdbbba
 abbbdcdbbba is a palindrome.

 Process finished with exit code 0

 // If the word is not a palindrome
 This application helps you evaluate if a string is a palindrome or not
 Please enter the string: kennethpogi
 kennethpogi is not a palindrome.

 Process finished with exit code 0


 Write the algorithm below:
 -------------------------------------
 1. Start - The program initializes and prepares to check if a string is a palindrome.
 2. Input - The user is asked to input a string to check.
 3. Stack Initialization - An empty stack is created to store characters from the string.
 4. First Half Processing - The first half of the string is pushed onto the stack, character by character.
 5. Odd-Length Handling - If the string has an odd length, the middle character is skipped to ensure the correct comparison of the two halves.
 6. Second Half Processing - For each character in the second half of the string, the top element of the stack is popped and compared to the character. If there's a mismatch, the program returns false, indicating that the string is not a palindrome.
 7. Stack Check - If the stack is empty after the process, the string is a palindrome.
 8. Result - The program outputs whether the string is a palindrome or not, based on the comparisons.
 9. End - The program ends.
 */

import java.lang.*;
import java.util.Scanner;
public class PalindromeChecker {

    public static void main(String[] args){ // Main Method
        PalindromeChecker myProgram; // Instantiation of the PalindromeChecker class
        try{
            myProgram = new PalindromeChecker();
            myProgram.run(); //calling the run method and has a StackUnderflowException handling
        } catch (Exception e){ // For the StackUnderflowException
            e.printStackTrace();
        }
        System.exit(0); // Ending the program
    } // end of main method

    /**
     * This method starts the program and handles user input.
     */
    public void run()throws Exception{
        Scanner keyboard = new Scanner(System.in);

        // User Input
        System.out.println("This application helps you evaluate if a string is a palindrome or not");
        System.out.print("Please enter the string: ");
        String input = keyboard.nextLine();

        // Output Result
        if (isPalindrome(input)) {
            System.out.println(input + " is a palindrome.");
        }
        else {
            System.out.println(input + " is not a palindrome.");
        }
    } // end of run method

    /**
     * This method checks if the input string is a palindrome using a stack.
     *
     * @param string the input string to check
     * @return true if the string is a palindrome, false otherwise
     * @throws StackUnderflowException if an attempt to pop from an empty stack occurs
     */
    public boolean isPalindrome(String string) throws StackUnderflowException{
        MyStack<Character> stack = new MyStack<>(); // Initialize the MyStack
        int index = 0;
        Character topSymbol;

        // Push the First Half of the String onto the Stack
        while (index < string.length()/2){
            stack.push(string.charAt(index));
            index++;
        }

        // Skip Middle Character for Odd-Length Strings
        if (string.length() % 2 != 0) {
            index += 1;
        }

        // Pop Characters from the Stack and Compare
        for (;index<string.length(); index++){
            topSymbol = stack.pop();
            if (topSymbol.charValue() != (string.charAt(index)) ) {
                return false; // Not a palindrome if characters don't match
            }
        }

        // Check if Stack is Empty
        if (stack.isEmpty())
            return true; // Palindrome
        else
            return false; // Not a Palindrome
    } // end of isPalindrome method
} // end of PalindromeChecker class
