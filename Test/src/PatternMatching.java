import java.util.Scanner;

public class PatternMatching {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String searchText = scanner.next();

        String regex = "([!@#$%^&*()_+{}|:\"<>?\\[\\];',./\\\\])";

        String output = searchText.replaceAll(regex, "\\\\\\\\$1");

        System.out.println(output);
    }
}


