class ClassA{
    void method1(){
        System.out.println("Inside the parent class Method 1");
    }
    void method2(){
        System.out.println("Inside the Parent class Method 2");
    }
}

public class J2099RunTimePoly extends ClassA {
    void method1(){
        System.out.println("Inside the child class Method 1");
    }
    void method3(){
        System.out.println("Inside the child class Method 3");
    }

    public static void main(String[] args) {
        ClassA classA = new J2099RunTimePoly();
        classA.method1();
        classA.method2();
        //classA.method3();   //Generate an error to the Parent reference
    }
}
