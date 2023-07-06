package Collections;

import java.util.ArrayList;

public class J8004ArrayList {
    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        J8004ArrayList j8004ArrayList = new J8004ArrayList();
        J8004ArrayList j8004ArrayList1 = new J8004ArrayList();
        J8004ArrayList j8004ArrayList2 = new J8004ArrayList();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//        arrayList.add(5);
//        arrayList.add(6);
        arrayList.add(j8004ArrayList);
        arrayList.add(j8004ArrayList1);
        arrayList.add(j8004ArrayList2);


        System.out.println(arrayList.remove(1));
        System.out.println(arrayList);
    }
}
