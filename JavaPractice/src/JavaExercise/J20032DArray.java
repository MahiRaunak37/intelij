package JavaExercise;

import java.util.Scanner;

public class J20032DArray {
    public static void main(String[] args) {
        int[][] array = new int[3][3];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("Enter the value at position [" + i + "][" + j + "] :- ");
                array[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < array.length; i++) {
            int sum =0;
            for (int j = 0; j < array[i].length; j++) {
                sum = sum + array[i][j];

            }
            System.out.println("Sum of "+(i+1)+"row = "+sum);
        }

        for (int i = 0; i < array.length; i++) {
            int sum =0;
            for (int j = 0; j < array[i].length; j++) {
                sum = sum + array[j][i];

            }
            System.out.println("Sum of "+(i+1)+"column = "+sum);
        }
    }
}
