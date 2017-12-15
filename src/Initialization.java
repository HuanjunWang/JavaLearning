/**
 * Created by Echo Wang on 12/14/2017.
 */

class InitExample1{
    private boolean booleanVar;
    private short shortVar;
    private int intVar;
    private char charVar;
    private float floatVar;
    private double doubleVar;
    private String stringVar;

    @Override
    public String toString() {
        return "booleanVar:" + booleanVar + " shortVar:" + shortVar + " intVar:" + intVar + " charVar:" + charVar + " floatVar:" + floatVar + " doubleVar:" + doubleVar + " stringVar:" + stringVar;
    }
}

class InitExample2{
    private boolean booleanVar;
    private short shortVar;
    private int intVar;
    private char charVar;
    private float floatVar;
    private double doubleVar;
    private String stringVar;

    public InitExample2(){
        booleanVar = true;
        shortVar = 1;
        intVar = 1;
        charVar = 'a';
        floatVar = 1.0f;
        doubleVar = 1.0f;
        stringVar = new String("Hello Java");
    }

    @Override
    public String toString() {
        return "booleanVar:" + booleanVar + " shortVar:" + shortVar + " intVar:" + intVar + " charVar:" + charVar + " floatVar:" + floatVar + " doubleVar:" + doubleVar + " stringVar:" + stringVar;
    }
}

class InitExample3{
    private boolean booleanVar = true;
    private short shortVar = 2;
    private int intVar = shortVar * 2;
    private char charVar = 'b';
    private float floatVar = 2.0f;
    private double doubleVar = 2.0f;
    private String stringVar = new String("Hello Again");

    {
        System.out.println("InitExample3 Init Block 1");
        shortVar = 3;
    }

    public InitExample3(){
        System.out.println("InitExample3 Construct 1");
    }

    public InitExample3(boolean booleanVar){
        System.out.println("InitExample3 Construct 2");
        this.booleanVar = booleanVar;
        shortVar = 1;
        intVar = 1;
        charVar = 'a';
        floatVar = 1.0f;
        doubleVar = 1.0f;
        stringVar = new String("Hello Java");
    }
    {
        System.out.println("InitExample3 Init Block 2");
        doubleVar = 3.0f;
    }

    @Override
    public String toString() {
        return "booleanVar:" + booleanVar + " shortVar:" + shortVar + " intVar:" + intVar + " charVar:" + charVar + " floatVar:" + floatVar + " doubleVar:" + doubleVar + " stringVar:" + stringVar;
    }
}


public class Initialization {
    public static void main(String[] args){
        InitExample1 ex1 = new InitExample1();
        System.out.println(ex1);

        InitExample2 ex2 = new InitExample2();
        System.out.println(ex2);

        InitExample3 ex3 = new InitExample3();
        System.out.println(ex3);

        InitExample3 ex3_1 = new InitExample3(false);
        System.out.println(ex3_1);

    }
}
