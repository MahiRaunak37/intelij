package Collections;

import java.util.Scanner;

public class J8002LastWordFormString {
        StringBuilder LastWord(String string){
            StringBuilder stringBuilder = new StringBuilder();
            String[] stringArray = string.trim().split(" ");
            for(int i=0;i<stringArray.length;i++){
                //System.out.print(stringArray[i].replace(" ",""));
                stringBuilder.append((stringArray[i]).charAt(stringArray[i].length()-1));
            }
            return stringBuilder;
        }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        J8002LastWordFormString j8002LastWordFormString = new J8002LastWordFormString();
        System.out.println(j8002LastWordFormString.LastWord(scanner.nextLine()));

    }
}
