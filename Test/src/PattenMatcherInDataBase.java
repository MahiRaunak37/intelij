import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattenMatcherInDataBase {
    public static void main(String[] args) {
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String searchText = scanner.next();
        ArrayList<Character> arrayList = new ArrayList<Character>();
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();

        Pattern specialChar = Pattern.compile("[!@#$%&*()+=|<>?{}\\[\\]~-]");
        Matcher hasSpecialChar = specialChar.matcher(searchText);

        char[] charArray = searchText.toCharArray();


        for(int i=0;i<charArray.length;i++){
            if(hasSpecialChar.find()){
                integerArrayList.add(i,hasSpecialChar.start());
             }
        }
        System.out.println(integerArrayList);



        for(int i=0;i<charArray.length;i++){
            arrayList.add(charArray[i]);
        }

        for(int i=0;i<arrayList.size();i++){
            for(int j=0;j<integerArrayList.size();j++){
                if(i==integerArrayList.get(j)){
                    arrayList.add(i,'\\');
                    arrayList.add(i,'\\');
                    System.out.println("i and j "+ i+" "+j);
                }
            }

        }
        StringBuilder stringBuilder = new StringBuilder(arrayList.size());
        for(Character character : arrayList)
            stringBuilder.append(character);

        String finalSearchText = stringBuilder.toString();
        System.out.println(finalSearchText);

    }
}
