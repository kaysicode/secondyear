/*
Name of Student: MAYO, Kenneth Charles P.
Date: October 10, 2024
*/
import java.util.LinkedList; // access the LinkedList class from package util
/**
 *  Template for a polynomial
 */
public class Polynomial {
    private LinkedList<Term> terms; // data member to reference a
    // LinkedList representing a polynomial

    /**
     * Constructs a LinkedList for Terms that is initially empty
     */
    public Polynomial() throws Exception {
        terms = new LinkedList<Term>();
    }

    /**
     * Adds a Term to the polynomial such that the terms are arranged following a decreasing
     * order of degrees.
     * This method inserts a Term in the polynomial at the appropriate location if the degree of the term
     * is not equal to a degree of an existing Term.
     * if the degree of the Term is equal to a degree of an existing Term. the coefficient of the
     * existing Term is updated by adding the coefficient of the Term being added. If the updated coefficient
     * equals zero, the Term is removed from the polynomial.
     */
    public void addTerm(Term newTerm) throws Exception {
        int ctr;
        boolean found = false;
        Term currTerm = null;

        for (ctr = 0; ctr < terms.size(); ctr++) { // size method of LinkedList is used
            currTerm = terms.get(ctr); // get method of LinkedList is used
            if (currTerm.getDegree() <= newTerm.getDegree()) {
                found = true;
                break;
            }
        }

        if (!found) {
            terms.add(newTerm); // add method of LinkedList is used
        } else {
            if (currTerm.getDegree() < newTerm.getDegree()) {
                terms.add(ctr, newTerm); // alternative add method of LinkedList is used
            } else {
                currTerm.setCoefficient(currTerm.getCoefficient() + newTerm.getCoefficient());
                if (currTerm.getCoefficient() == 0) {
                    terms.remove(ctr); // remove method of the LinkList class is used
                }
            }
        }

    }

    /**
     * Returns a string representing this polynomial with the following sample form 3x^2 - 5X + 3
     */
    public String toString() {
        String s = "";
        if (terms == null)
            return " ";
        for (int ctr = 0; ctr < terms.size(); ctr++) {
            Term currTerm = terms.get(ctr);

            if (currTerm.getCoefficient() > 0) {
                if (ctr != 0) {
                    s = s + " +";
                }
            } else {
                s = s + " -";
            }
            if (currTerm.getCoefficient() != 1 || currTerm.getDegree() == 0) {
                s = s + " " + Math.abs(currTerm.getCoefficient());
            }
            switch (currTerm.getDegree()) {
                case 0:
                    break;
                case 1:
                    s = s + (terms.get(0)).getLiteral();
                    break;
                default:
                    s = s + (terms.get(0)).getLiteral() + "^" + currTerm.getDegree();
            }
        }
        return s;
    }

    /**
     * Returns the value of this polynomial if its literal is substituted
     * by the specified given value.
     */
    public double evaluate(double value) throws Exception {
        double sum = 0;
        for (int ctr = 0; ctr < terms.size(); ctr++) {
            Term currTerm = terms.get(ctr);
            sum += currTerm.getCoefficient() * Math.pow(value, currTerm.getDegree());
        }
        return sum;
    }

    /**
     * Sets this polynomial to a given LinkedList of Term
     */
    public void setTerms(LinkedList<Term> t) {
        terms = t;
    }

    /**
     * Returns this polynomial as a LinkedList of Term
     */
    public LinkedList<Term> getTerms() {
        return terms;
    }

    /**
     * public Polynomial add(Polynomial otherPolynomial) throws Exception
     * Adds otherPolynomial to this polynomial
     * 1. Declare (let) result as a Polynomial that will eventually represent the sum polynomial
     * 2. Construct a new LinkedList of Term(i.e. resultTerms) that will eventually hold
     * the Terms of the sum Polynomial
     * 3. Construct a copy of each term of this (first) Polynomial
     * and add the constructed Term to the constructed LinkedList (resultTerms)
     * 4. Let resultTerms be the terms of result(i.e. the sum polynomial)
     * 5. For each (get each) term of the other polynomial, construct a copy
     * of the term and assign such copy to sTerm.
     * 6. Add sTerm to the sum polynomial (result)
     * 7.If result polynomial has no term, let result have the term 0x^0
     * 8. return result.
     */
    public Polynomial add(Polynomial otherPolynomial) throws Exception {
        Polynomial result = new Polynomial();
        LinkedList<Term> resultTerms = new LinkedList<Term>();
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            resultTerms.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }

        // Put missing statement here
        result.setTerms(resultTerms);

        for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
            Term currentTerm = otherPolynomial.getTerms().get(ctr2);
            Term sTerm = new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree());
            result.addTerm(sTerm);
        }
        if (result.getTerms().size() == 0)
            result.addTerm(new Term(0, 'x', 0));
        return result;
    }

    /**
     * Subtracts otherPolynomial from this polynomial
     * <p>
     * 1. Declare (let) result as a Polynomial that will eventually represent the difference polynomial
     * 2. Construct a new LinkedList of Term(i.e. resultTerms) that will eventually hold
     * the Terms of the difference Polynomial
     * 3. Construct a copy of each term of this (first) Polynomial
     * and put the constructed Term to the constructed LinkedList (resultTerms)
     * 4. Let resultTerms be the terms of result(i.e. the sum polynomial)
     * 5. For each (get each) term of the other polynomial, construct a copy of the term
     * and assign such copy to sTerm.
     * 6. Multiply the numerical coefficient field of sTerm by -1.
     * 7. Add sTerm to the difference polynomial (result)
     * <p>
     * 8.If result polynomial has no term, let result have the term 0x^0
     * 9. return result.
     */
    public Polynomial subtract(Polynomial otherPolynomial) throws Exception {
        Polynomial result = new Polynomial();  // Step 1: Declare the result Polynomial
        LinkedList<Term> resultTerms = new LinkedList<Term>();  // Step 2: Construct a new LinkedList for result terms

        // Step 3: Copy each term from the current (first) polynomial to resultTerms
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            resultTerms.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }

        // Step 4: Assign resultTerms as the terms of the result polynomial
        result.setTerms(resultTerms);

        // Step 5: For each term in otherPolynomial, create a copy and negate the coefficient
        for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
            Term currentTerm = otherPolynomial.getTerms().get(ctr2);
            Term sTerm = new Term(currentTerm.getCoefficient() * -1, currentTerm.getLiteral(), currentTerm.getDegree());  // Step 6: Negate the coefficient
            result.addTerm(sTerm);  // Step 7: Add sTerm to the result polynomial
        }

        // Step 8: If the result polynomial has no terms, add the term 0x^0
        if (result.getTerms().size() == 0) {
            result.addTerm(new Term(0, 'x', 0));
        }

        return result;  // Step 9: Return the result
    }

    /**
     * Multiplies this polynomial by otherPolynomial.
     * The method assumes that the polynomials have the same literals and
     * it follows the prescription of the Term class.
     */
    public Polynomial multiply(Polynomial otherPolynomial) throws Exception {
        Polynomial result = new Polynomial();
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm1 = this.getTerms().get(ctr);
            for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
                Term currentTerm2 = otherPolynomial.getTerms().get(ctr2);
                double pCoef = currentTerm2.getCoefficient() * currentTerm1.getCoefficient();
                int pDegree = currentTerm2.getDegree() + currentTerm1.getDegree();
                result.addTerm(new Term(pCoef, currentTerm1.getLiteral(), pDegree));
                // Invoke appropriate method to add a term to product polynomial
            } // end of second for ( for ctr2)
        } // end of first for (for ctr)
        if (result.getTerms().size() == 0)
            result.addTerm(new Term(0, 'x', 0));
        return result;
    }

    /**
     * Divides this polynomial by divisor polynomial
     */
    public Quotient divide(Polynomial divisor) throws Exception {
        Quotient result = new Quotient();
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        LinkedList<Term> dividend = new LinkedList<Term>();
        Term qTerm;
        Polynomial subtrahend = new Polynomial();
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            dividend.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }
        remainder.setTerms(dividend);
        while (((remainder != null)) && ((remainder.getTerms().get(0)).getDegree() >= (
                divisor.getTerms().get(0)).getDegree())) {
            Term numTerm = remainder.getTerms().get(0);
            Term divTerm = divisor.getTerms().get(0);
            qTerm = new Term(numTerm.getCoefficient() / divTerm.getCoefficient(), numTerm.getLiteral(),
                    numTerm.getDegree() - divTerm.getDegree());
            quotient.addTerm(qTerm);
            LinkedList<Term> pQTerm = new LinkedList<Term>();
            pQTerm.add(qTerm);
            Polynomial multiplier = new Polynomial();
            multiplier.setTerms(pQTerm);
            subtrahend = multiplier.multiply(divisor);
            remainder = remainder.subtract(subtrahend);
        }
        if (quotient.getTerms().size() == 0)
            quotient.addTerm(new Term(0, 'x', 0));
        result.setQuotientP(quotient);
        if (remainder.getTerms().size() == 0)
            remainder.addTerm(new Term(0, 'x', 0));
        result.setRemainderP(remainder); // Invoke appropriate method to set remainder member of quotient
        return result;
    }
}
