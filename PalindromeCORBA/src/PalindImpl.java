import PalindromeApp.PalindromePOA;

public class PalindImpl extends PalindromePOA {

    @Override
    public boolean isPalindrome(String value) {
        StringBuilder s = new StringBuilder(value);
        s.reverse();
        return value.equals(s.toString());
    }
}
