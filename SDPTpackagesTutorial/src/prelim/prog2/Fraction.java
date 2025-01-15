package prelim.prog2;

public class Fraction {
        private int numerator;
        private int denominator;


        public Fraction(){
            this.numerator = 0;
            this.denominator = 0;
        }
        public Fraction(int numerator){
            this.numerator = numerator;
            this.denominator = 0;
        }
        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public int getNumerator() {
            return this.numerator;
        }

        public void setNumerator(int numerator) {

            this.numerator = numerator;
        }

        public int getDenominator() {
            return denominator;
        }

        public void setDenominator(int denominator) {
            this.denominator = denominator;
        }

        public Fraction add(Fraction other) {
            int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
            int newDenominator = this.denominator * other.denominator;
            return new Fraction(newNumerator, newDenominator);
        }

        public Fraction subtract(Fraction other) {
            int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
            int newDenominator = this.denominator * other.denominator;
            return new Fraction(newNumerator, newDenominator);
        }

        public Fraction multiply(Fraction other) {
            int newNumerator = this.numerator * other.numerator;
            int newDenominator = this.denominator * other.denominator;
            return new Fraction(newNumerator, newDenominator);
        }

        public Fraction divide(Fraction other) {
            int newNumerator = this.numerator * other.denominator;
            int newDenominator = this.denominator * other.numerator;
            return new Fraction(newNumerator, newDenominator);
        }

        public Fraction reduce() {
            int gcd = gcd(this.numerator, this.denominator);
            int newNumerator = this.numerator / gcd;
            int newDenominator = this.denominator / gcd;
            return new Fraction(newNumerator, newDenominator);
        }

        private int gcd(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }

        public double toDecimal() {
            return (double) this.numerator / this.denominator;
        }

        public String toString() {
            return numerator + "/" + denominator;
        }
    }


