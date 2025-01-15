public class otherTRIALS {
    public static void main(String[] args){
        int a = 48;
        int b = 18;

        System.out.println("THE GCD of A and B is  : " + gcd(a, b));
    }

    public static int gcd(int a, int b) {
        System.out.println("The current value of a is " + a + " and b is " + b );

        if (b == 0) {
            System.out.println("Base case reached, returning a = " + a);
            return a;
        }

        System.out.println("Calling gcd with arguments: b = " + b + ", a % b = " + (a % b));
        int result = gcd(b, a%b);

        System.out.println("Returned from recursive call, result = " + result);
        return result;
    }
}
