import java.util.Scanner;

public class TestingN {
    private static boolean check(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean whitespace = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = false;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = false;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            } else if (Character.isWhitespace(ch)) {
                whitespace = false;
            }
        }
            return  true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Name:- ");
        String name = scanner.nextLine();
        TestingN testingN = new TestingN();
        System.out.println(testingN.check(name));
    }
}
