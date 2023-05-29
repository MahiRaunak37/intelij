public class Testing
{

    public static void main(String[] args)
    {
        String name="abc dsf";
        if (name.isEmpty())
            System.out.println("Name is required");
        else
        {
            for (int i = 0; i<name.length(); i++) {
                if (Character.isUpperCase(name.charAt(i)))
                {
                    System.out.println("Name should not  be in uppercase");
                    break;
                }
                else if(Character.isWhitespace(name.charAt(i)))
                {
                    System.out.println("Name should not contain white spaces");
                    break;
                }
                else if(Character.isDigit(name.charAt(i)))
                {
                    System.out.println("Name should not contain digit");
                    break;
                }
                else if(Character.isWhitespace(name.charAt(i)))
                {
                    System.out.println("Name should not contain white spaces");
                    break;
                }
                else
                {

                    System.out.println("valid name");
                    break;

                }

            }
        }

    }

}