package JavaExercise;

import java.util.Scanner;

/**
 * Write a program to find the pair of sum of given number from an array.
 * Ex:*   int Numbers[] = {1,3,2,4,2,5,6,3,4,6,13,7,3,8,5,10,4,7,9,6,12,11};
 * int Sum = 15;
 * Output should be:
 * [3,12], [5,10], [6,9], [7,8], ..........
 * Condition:
 * There should not be duplicate pair.
 */

public class J2001SumOfPair {
    int[] sorting(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    int uniqueNumber(int[] arr) {
        int n = arr.length;
        if (n == 0 || n == 1) {
            return n;
        }
        int[] temp = new int[n];
        int j = 0;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                temp[j++] = arr[i];
            }
        }

        temp[j++] = arr[n - 1];

        // Changing the original array
        for (int i = 0; i < j; i++) {
            arr[i] = temp[i];
        }
        return j;
    }

    void display(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    void pairsDisplay(int[] sortedArray, int arrLengh, int sum) {
        System.out.println(sum);
        for (int i = 0; i < arrLengh; i++) {
            for (int j = 0; j < arrLengh; j++) {
                if (sortedArray[i] <= sortedArray[j]) {
                    if ((sortedArray[i] + sortedArray[j]) == sum) {
                        System.out.print("[" + sortedArray[i] + ", " + sortedArray[j] + "]");
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        J2001SumOfPair j2001SumOfPair = new J2001SumOfPair();
        Scanner scanner = new Scanner(System.in);
        int[] number = new int[]{1, 3, 2, 4, 2,15,0, 5, 6, 3, 4, 6, 13, 7, 3, 8, 5, 10, 4, 7, 9, 6, 12, 11, 2, 3, 4, 5, 67, 8, 9, 4, 4, 2, 2};
        int[] SortedArray = j2001SumOfPair.sorting(number);
        j2001SumOfPair.display(SortedArray);
        int uniqueNumber = j2001SumOfPair.uniqueNumber(SortedArray);
        System.out.println();
        j2001SumOfPair.display(SortedArray, uniqueNumber);
        System.out.println();
        System.out.print("Enter the number:- ");
        int sum = scanner.nextInt();
        System.out.println("------------------");
        j2001SumOfPair.pairsDisplay(SortedArray, uniqueNumber, sum);
    }
}
