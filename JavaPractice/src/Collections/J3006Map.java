package Collections;

import java.util.HashMap;

public class J3006Map{
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<Integer,String>();
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        map.put(4,"four");

        System.out.println(map.get(1));
    }
}