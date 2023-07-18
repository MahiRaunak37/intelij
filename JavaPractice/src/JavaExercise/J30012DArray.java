package JavaExercise;

import java.util.Scanner;

public  class J30012DArray{
    void TwoDArray(int [][] array)
    {
        for(int i =0;i< array.length;i++){
            for(int j=0;j< array[i].length;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }
    void patternOf2Darray(int row, int col,int [][]array){
        //System.out.println("row "+row+"col "+col);
        row = row -1;
        col = col -1;
        for(int i=0;i< array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(i==row){
                   array[i][j]=0;
                }
                if(j==col){
                    array[i][j]=0;
                }
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,1,1},{1,1,1},{1,1,1}};
        J30012DArray j30012DArray = new J30012DArray();
        int row,col;
        Scanner scanner = new Scanner(System.in);
        j30012DArray.TwoDArray(array);

        System.out.print("Enter the value of row no. then column no.:- ");
        row = scanner.nextInt();    col = scanner.nextInt();
        j30012DArray.patternOf2Darray(row,col,array);

    }
}