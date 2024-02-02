package Java8Feature;
@FunctionalInterface
interface lambda{
    void method();
}
@FunctionalInterface
interface lambda2{
    void method2(int m, int n);
}
@FunctionalInterface
interface  lambda3{
    int method3(int m, int n);
}

public class J3500LambdaExpression{
    public static void main(String[] args) {
        System.out.println("Implementing the Lambda Expression");
        lambda x = () -> System.out.println("Hello world");
        x.method();

        lambda2 y = (m,n) -> System.out.println("Sum of numbers");
        y.method2(2,3);

        lambda3 z = (int a, int b) -> {
            if(a>b)
                return  a;
            else
                return b;
        };
        System.out.println("Greater Number:- "+z.method3(44,77));
    }
}