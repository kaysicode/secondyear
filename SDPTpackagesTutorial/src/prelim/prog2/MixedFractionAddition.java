package prelim.prog2;

public class MixedFractionAddition {
    public static void main(String[] args) {
        // First mixed fraction: 2 1/2
        int whole1 = 2;
        int numerator1 = 1;
        int denominator1 = 2;

        // Second mixed fraction: 1 3/4
        int whole2 = 1;
        int numerator2 = 3;
        int denominator2 = 4;

        // Convert mixed fractions to improper fractions
        int improperNumerator1 = whole1 * denominator1 + numerator1;
        int improperNumerator2 = whole2 * denominator2 + numerator2;

        // Find a common denominator
        int commonDenominator = lcm(denominator1, denominator2);

        // Adjust numerators based on common denominator
        improperNumerator1 *= commonDenominator / denominator1;
        improperNumerator2 *= commonDenominator / denominator2;

        // Add the numerators
        int sumNumerator = improperNumerator1 + improperNumerator2;

        // Simplify the result
        int wholeResult = sumNumerator / commonDenominator;
        int remainder = sumNumerator % commonDenominator;
        int gcd = gcd(remainder, commonDenominator);
        remainder /= gcd;
        commonDenominator /= gcd;

        // Display the result
        System.out.println("Sum: " + wholeResult + " " + remainder + "/" + commonDenominator);
    }

    // Method to find the least common multiple (LCM) of two numbers
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    // Method to find the greatest common divisor (GCD) of two numbers (Euclidean algorithm)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
