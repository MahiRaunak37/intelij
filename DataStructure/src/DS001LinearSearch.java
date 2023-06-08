import java.util.ArrayList;
import java.util.Scanner;

/**
 * Topic:- linear search in java
 * by:- mahi raunak
 * Date:- 08/06/2023
 */
interface LinearSearch {
    void input();

    void display();

    void search(int n);
}

public class DS001LinearSearch implements LinearSearch {


    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Override
    public void input() {
        System.out.print("Enter the length of Array:- ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the element at index:- [" + i + "] :- ");
            arrayList.add(scanner.nextInt());
        }
    }

    @Override
    public void display() {
        System.out.print("Array is :- ");

        for (Integer integer : arrayList) {
            System.out.print(integer + " ");
        }
    }

    @Override
    public void search(int n) {
        boolean status = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == n) {
                System.out.print(n + " is find at index at " + i);
                return;
            }
        }
        System.out.println("Number is not found in list");
    }

    public static void main(String[] args) {

        DS001LinearSearch ds001LinearSearch = new DS001LinearSearch();
        ds001LinearSearch.input();
        ds001LinearSearch.display();
        System.out.println();
        System.out.print("Enter the number to search:- ");
        ds001LinearSearch.search(new Scanner(System.in).nextInt());
    }
}
