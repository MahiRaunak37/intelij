import java.util.regex.*;
public class Validation {
    public static void main(String[] args) {
        String str = "1234";
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[-+!@#$%^&*., ?]).+$";

        Pattern pattern = Pattern.compile(regex);

        if(str == null){
            System.out.println("Please fill something");
        }
        Matcher matcher = pattern.matcher(str);

        if(matcher.matches())
            System.out.println("InValid Input");
        else
            System.out.println("valid Input");
    }
}



/*
import java.util.regex.*;

class GFG {


    public static void isAllPresent(String str)
    {
        String regex = "^(?=.*[a-z])(?=."
                       + "*[A-Z])(?=.*\\d)"
                       + "(?=.*[-+_!@#$%^&*., ?]).+$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // then print No
        if (str == null) {
            System.out.println("No");
            return;
        }

        // Find match between given string
        // & regular expression
        Matcher m = p.matcher(str);

        // Print Yes if string
        // matches ReGex
        if (m.matches())
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    // Driver Code
    public static void main(String args[])
    {
        // Given string
        String str = "#GeeksForGeeks123@";

        // Function Call
        isAllPresent(str);
    }
}
 */