package prelim.prog2;
import java.util.Scanner;
public class FractionArithmetic {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Fraction fraction1 = new Fraction();
            Fraction fraction2 = new Fraction();

            System.out.print("Enter a value for numerator for first fraction : ");
            int numerator = scanner.nextInt();

            System.out.print("Enter a value for denominator for first fraction : ");
            int denominator = scanner.nextInt();

            if(numerator != 0 && denominator != 0) {
                fraction1 = new Fraction(numerator, denominator);
            } else if (denominator == 0) {
                fraction1 = new Fraction(numerator);
            } else {
                fraction1 = new Fraction();
            }
            System.out.println("Your first fraction has a numerator of : " + fraction1.getNumerator() +
                    "\n" + " And the denominator of : " + fraction1.getDenominator());

            System.out.print("Enter a value for numerator for second fraction : ");
            int numerator2 = scanner.nextInt();

            System.out.print("Enter a value for denominator for second fraction : ");
            int denominator2 = scanner.nextInt();

            if(numerator2 != 0 && denominator2 != 0) {
                fraction2 = new Fraction(numerator2, denominator2);
            } else if (denominator2 == 0) {
                fraction2 = new Fraction(numerator2);
            } else {
                fraction2 = new Fraction();
            }
            System.out.println("Your second fraction has a numerator of : " + fraction2.getNumerator() +
                    "\n" +  " And the denominator of : " + fraction2.getDenominator());



            int choice;
            do {
                System.out.println("""
                ======================================
                What do you want to do?
                    1. Enter value of fraction 1
                    2. Enter value of fraction 2
                    3. Add fractions
                    4. Subtract fractions
                    5. Multiply fractions
                    6. Divide fractions
                    7. Reduce fractions
                    8. Quit
                =====================================""");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the numerator of fraction 1:");
                        int newNumerator1 = scanner.nextInt();
                        System.out.println("Enter the denominator of fraction 1:");
                        int newDenominator1 = scanner.nextInt();
                        if(newNumerator1 != 0 && newDenominator1 != 0) {
                            fraction1 = new Fraction(newNumerator1, newDenominator1);
                        } else if (newDenominator1 == 0) {
                            fraction1 = new Fraction(newNumerator1);
                        } else {
                            fraction1 = new Fraction();
                        }
                        System.out.println("Your new first fraction has a numerator of : " + fraction1.getNumerator() +
                                " And the denominator of : " + fraction1.getDenominator());
                        break;
                    case 2:
                        System.out.println("Enter the numerator of fraction 2:");
                        int newNumerator2 = scanner.nextInt();
                        System.out.println("Enter the denominator of fraction 2:");
                        int newDenominator2 = scanner.nextInt();
                        if(newNumerator2 != 0 && newDenominator2 != 0) {
                            fraction2 = new Fraction(newNumerator2, newDenominator2);
                        } else if (newDenominator2 == 0) {
                            fraction2 = new Fraction(newNumerator2);
                        } else {
                            fraction2 = new Fraction();
                        }
                        System.out.println("Your new second fraction has a numerator of : " + fraction2.getNumerator() +
                                " And the denominator of : " + fraction2.getDenominator());
                        break;
                    case 3:
                            Fraction sum = fraction1.add(fraction2);
                            System.out.println("Sum: " + sum + " (" + sum.toDecimal() + ")");
                        break;
                    case 4:
                            Fraction difference = fraction1.subtract(fraction2);
                            System.out.println("Difference: " + difference + " (" + difference.toDecimal() + ")");
                        break;
                    case 5:
                            Fraction product = fraction1.multiply(fraction2);
                            System.out.println("Product: " + product + " (" + product.toDecimal() + ")");
                        break;
                    case 6:
                            Fraction quotient = fraction1.divide(fraction2);
                            System.out.println("Quotient: " + quotient + " (" + quotient.toDecimal() + ")");
                        break;
                    case 7:
                            System.out.println("Reduced fraction 1: " + fraction1.reduce());
                            System.out.println("Reduced fraction 2: " + fraction2.reduce());
                        break;
                    case 8:
                        System.out.println("Exiting program");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } while (choice != 8);

            scanner.close();
        }
    }


