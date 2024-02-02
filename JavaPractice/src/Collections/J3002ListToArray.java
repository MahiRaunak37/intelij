package Collections;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class J3002ListToArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(2);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);
        //To convert List into Array
        Object []array = arrayList.toArray();
        for(int i=0;i<array.length;i++){
            System.out.print((int)array[i]+" ");
        }

    }
}