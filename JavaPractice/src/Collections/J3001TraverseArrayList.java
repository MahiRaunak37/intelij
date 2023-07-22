package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class J3001TraverseArrayList {
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

        //getting the value from list using for loop
        for (int i=0;i<arrayList.size();i++){
            System.out.print(arrayList.get(i)+" ");
        }
        System.out.println();
        //Getting the value from for-each loop
        for (int value : arrayList)
            System.out.print(value+" ");

        //Getting the value from Iterator
        System.out.println();
        Iterator<Integer> iterator = arrayList.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
    }

}