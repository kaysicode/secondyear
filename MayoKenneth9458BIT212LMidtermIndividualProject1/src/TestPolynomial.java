/*
Name of Student: MAYO, Kenneth Charles P.
Date: October 10, 2024
*/
public class TestPolynomial {
    public static void main(String[] args) {
        try {
            Polynomial p = new Polynomial();
            p.addTerm(new Term(1,'x',2));
            p.addTerm(new Term(4, 'x', 3));
            p.addTerm(new Term(-3, 'x', 1));
            p.addTerm(new Term(1, 'x', 0));
            Polynomial other = new Polynomial();
            other.addTerm(new Term(2,'x',1));
            other.addTerm(new Term(-1,'x',2));
            System.out.println("Sample Polynomial: " + p.toString());
            System.out.println("Value of the sample polynomial at x=2: " + p.evaluate(2));
            System.out.println("\nExample of addition of polynomials");
            Polynomial sP = p.add(other);
            System.out.println("(" + p.toString() + ") + (" + other.toString() + ") = " + sP.toString());
            System.out.println("\nExample of subtraction of polynomials");
            Polynomial dP = p.subtract(other);
            System.out.println("(" + p.toString() + ") - (" + other.toString() + ") = " + dP.toString());
            System.out.println("\nExample of multiplication of polynomials");
            Polynomial pP = p.multiply(other);
            System.out.println("(" + p.toString() + ") * (" + other.toString() + ") = " + pP.toString());
            System.out.println("\nExample of division of polynomials");
            Quotient qP = p.divide(other);
            System.out.println("(" + p.toString() + ") / (" + other.toString() + ") = " + qP.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } // end of catch block
    } // end of main
} // end of TestPolynomial class
