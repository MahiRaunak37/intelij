package Collections;

public class J8003Array {
    public static void main(String[] args) {
        J8003Array [] objectArray;
        J8003Array j8003Array1 = new J8003Array();
        J8003Array j8003Array2 = new J8003Array();
        J8003Array j8003Array = new J8003Array();

        objectArray = new J8003Array[]{j8003Array, j8003Array1, j8003Array2};
        System.out.println(objectArray);

    }
}
