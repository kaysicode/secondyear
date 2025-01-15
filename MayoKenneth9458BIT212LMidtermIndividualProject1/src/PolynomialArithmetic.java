/*
Name of Student: MAYO, Kenneth Charles P.
Date: October 10, 2024
*/
import java.util.Scanner;
/**
 *  Here, I added some method like the methods of subtractPolynomials(), multiplyPolynomials(), and the dividePolynomials() methods
 *  and after that, I implemented the code by just copying the process or the logic in the addPolynomial() method since, it is just same thing.
 *  However, the difference is that I will just change the add() method and change it to their specifically method like just called
 *  subtract(), multiply(), and divide() method in the polynomial class and after that, just change the Strings.
 */
public class PolynomialArithmetic {

    Scanner keyboard = new Scanner(System.in);
    public void run() throws Exception{
        byte choice = 0;
        while ( choice != 6 ) {
            showMenu();
            choice = readChoice((byte) 1, (byte) 6);
            switch (choice){
                case 1:
                    evaluatePolynomial();
                    break;
                case 2:
                    addPolynomials();
                    break;
                case 3:
                    subtractPolynomials();
                    break;
                case 4:
                    multiplyPolynomials();
                    break;
                case 5:
                    dividePolynomials();
                    break;
                case 6:
                    System.out.println("Thank you for using this program.");
            } // end of swicth
        } // end of while
    } // end of run
    private byte readChoice(byte low, byte high) throws Exception{
        byte choice=0;
        System.out.print("Enter your choice<"+ low + "... " + high + ">: ");
        choice = (byte) readInteger(low, high);
        return choice;
    }
    public void showMenu() {
        System.out.println("-----------------------MENU--------------------------");
        System.out.println("1. Evaluate a polynomial");
        System.out.println("2. Add two polynomials");
        System.out.println("3. Subtract a polynomial from another polynomial");
        System.out.println("4. Multiply two polynomials");
        System.out.println("5. Divide a polynomial by another polynomial");
        System.out.println("6. Quit");
        System.out.println("--------------------------------------------------------");
    }
    public void evaluatePolynomial() throws Exception{
        System.out.println("You want to evaluate a polynomial.");
        Polynomial p = readPolynomial();
        System.out.println("The polynomial entered is " + p.toString());
        System.out.print("What is the value to be assigned to variable of the polynomial? ");
        double value = readDouble();

        System.out.println("The polynomial evaluates to : "+ p.evaluate(value));
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }
    private int readInteger(int low, int high){
        boolean validInput = false;
        int value=0;
        while (!validInput){
            try{
                value = Integer.parseInt(keyboard.nextLine());
                if ( value < low){
                    System.out.print("The number must not be lower than "+ low + ". ");
                }
                else if ( value > high){
                    System.out.print("The number must not be greater than "+ high +". ");
                } else {
                    validInput = true;
                }
            } catch (Exception x){
                System.out.println("You have to enter an integer from " + low + " to " + high + ".");
            }
        }
        return value;
    }
    private double readDouble(){
        boolean validInput = false;
        double value=0;
        while (!validInput){
            try{
                value = Double.parseDouble(keyboard.nextLine());
                validInput = true;
            } catch (Exception x){
                System.out.println("You have to enter a number.");
            }
        }
        return value;
    }
    public Polynomial readPolynomial() throws Exception{
        Polynomial p = new Polynomial();
        int degree =-1;
        boolean validDegree = false;
        char literalCoefficient = 'x';
        System.out.println("The polynomial should involve one variable/literal only.");
        do {
            System.out.print("What is the literal coefficient of the polynomial in one variable? ");
            literalCoefficient = keyboard.nextLine().charAt(0);
        } while (!Character.isAlphabetic(literalCoefficient));
        do {

            System.out.print("What is the degree of the polynomial? ");
            degree = readInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);
            validDegree = true;
        } while (!validDegree);
        for (int x=degree; x>=0; x=x-1){
            Term term = readTerm(literalCoefficient, x);
            p.addTerm(term);
        }
        return p;
    }

    public Term readTerm(char literal, int degree)throws Exception{
        double nCoeff=0;
        System.out.print("Enter the numerical coefficient of the term with degree " + degree +": ");
        nCoeff = readDouble();
        Term term = new Term(nCoeff, literal, degree);
        return term;
    }
    public void addPolynomials() throws Exception {
        System.out.println("You want to add two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
                Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Sum of the polynomials : " + p1.add(p2));
        } else {
            System.out.println("The two polynomials cannot be added because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    public void subtractPolynomials() throws Exception {
        System.out.println("You want to subtract a polynomial from another.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial: " + p1.toString());
        System.out.println("Second polynomial: " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Difference of the polynomials: " + p1.subtract(p2)); // Here I just called the subtract method in the Polynomial Class
        } else {
            System.out.println("The two polynomials cannot be subtracted because they have different literals.");
        }
        System.out.println("Press enter to continue...");
        keyboard.nextLine();
    }

    public void multiplyPolynomials() throws Exception {
        System.out.println("You want to multiply two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Product of the polynomials : " + p1.multiply(p2)); // Here I just called the multiply method in the Polynomial Class
        } else {
            System.out.println("The two polynomials cannot be added because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    public void dividePolynomials() throws Exception {
        System.out.println("You want to divide two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Quotient of the polynomials : " + p1.divide(p2)); // Here I just called the divide method in the Polynomial Class
        } else {
            System.out.println("The two polynomials cannot be added because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    public static void main(String[] args) {
        PolynomialArithmetic program;
        try {
            program = new PolynomialArithmetic();
            program.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of main
} // end of PolynomialArithmetic class
