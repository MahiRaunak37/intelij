import java.util.Scanner;

public class J6000ContainCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String searchText = scanner.next();

        if(!searchText.contains("%")){
            searchText = "%"+searchText+"%";
        }
        System.out.println(searchText);
    }
}
