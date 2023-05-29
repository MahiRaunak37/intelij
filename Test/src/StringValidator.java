import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    public static void main(String[] args) {
        String inputString = "hel_lo";

        boolean hasUppercase = containsUppercase(inputString);
        boolean hasNumbers = containsNumbers(inputString);
        boolean hasSpecialChars = containsSpecialCharacters(inputString);

        if(hasUppercase || hasNumbers || hasSpecialChars)
            System.out.println("Invalid Input");
        else
            System.out.println("Valid Input");

    }

    private static boolean containsUppercase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsNumbers(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsSpecialCharacters(String str) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
