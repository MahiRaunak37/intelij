package Test;

public class Main {
    public static void main(String[] args) {

        // convert these strings to either lowercase or uppercase to create consistency
        System.out.println(isPalindrome("radar".toLowerCase()));

        // Naman wouldn't have been palindrome if toLowerCase method wasn't used
        System.out.println(isPalindrome("Naman".toLowerCase()));
        System.out.println(isPalindrome("12321".toLowerCase()));
        System.out.println(isPalindrome("12345".toLowerCase()));
    }

    private static boolean isPalindrome(String string) {

        String reversed = new StringBuilder(string).reverse().toString();
        return string.equals(reversed);
    }
}