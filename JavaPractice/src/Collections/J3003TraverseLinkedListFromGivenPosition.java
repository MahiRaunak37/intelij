package Collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class J3003TraverseLinkedListFromGivenPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList <Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);

        Iterator iterator = linkedList.iterator();
        while(iterator.hasNext())
            System.out.print(iterator.next()+" ");
        System.out.println();
        System.out.print("Enter the location form where traverse:- ");
        for(int i=scanner.nextInt();i<linkedList.size();i++)
            System.out.print(linkedList.get(i)+" ");
        System.out.println();
        System.out.println(linkedList.subList(scanner.nextInt(),linkedList.size()));

    }
}