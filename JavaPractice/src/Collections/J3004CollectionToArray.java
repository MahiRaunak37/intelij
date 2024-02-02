package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class J3004CollectionToArray {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(24);
        list.add(16);
        list.add(17);
        list.add(18);
        list.add(25);
        list.add(20);
        list.add(21);
        list.add(22);
        list.add(23);
        list.add(15);

        //Converting list to array in Object
        Object arr[] = list.toArray();

        System.out.print("\n Using for loop:- ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.print("\n Using Stram api:- " + list.stream().toList());

        System.out.print("\n Using foreach loop:-  ");
        for (Object object : arr) {
            System.out.print(object + " ");
        }
        System.out.print("\n Using While loop:- ");
        int i = 0;
        while (i < arr.length) {
            System.out.print(arr[i] + " ");
            i++;
        }

        System.out.print("\n Using Iterator:- ");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.print("\n Using do while loop:- ");
        int j = 0;
        do {
            System.out.print(arr[j] + " ");
            j++;
        } while (j < arr.length);
    }
}