import java.util.Scanner;

public class TestingSort {

    private static boolean isSpecialCharacter(char c)
    {
        return !(Character.isLetterOrDigit(c) || Character.isWhitespace(c));
    }

    private static String replaceSpecialCharacters(String input)
    {
        StringBuilder sb = new StringBuilder(input.length());
        for (char c : input.toCharArray())
        {
            if (isSpecialCharacter(c))
            {
                sb.append("//").append(c);
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(replaceSpecialCharacters(scanner.next()));
    }


}
