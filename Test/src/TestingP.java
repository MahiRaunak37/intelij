import java.util.regex.*;
public class TestingP {
    public static void main(String[] args) {

        String str = "abbcde";
        boolean result = false;

        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern specialChar = Pattern.compile("[!@#$%&*()+=|<>?{}\\[\\]~-]");
        Pattern whiteSpace = Pattern.compile(" ");

        Matcher hasUpperCase = upperCase.matcher(str);
        Matcher hasDigit = digit.matcher(str);
        Matcher hasSpecialChar = specialChar.matcher(str);
        Matcher hasWhiteSpace = whiteSpace.matcher(str);

        result = (hasUpperCase.find() || hasDigit.find() || hasSpecialChar.find() || hasWhiteSpace.find());

        if(result)
            System.out.println("Invalid Input");
        else
            System.out.println("Valid Input");
    }
}

