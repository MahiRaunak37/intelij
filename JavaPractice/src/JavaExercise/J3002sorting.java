package JavaExercise;

public class J3002sorting {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 1, 0, 0, 1, 1, 2};
        for(int i = 0; i < arr.length; i++) {
            if(arr[i]==0) {
                System.out.print(arr[i]+" ");
            }
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i]==1) {
                System.out.print(arr[i]+" ");
            }
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i]==2) {
                System.out.print(arr[i]+" ");
            }
        }
    }
}