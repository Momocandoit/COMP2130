/*
本当はこうやって書ける
void main() {
    System.out.println("Hello World!");
}
*/


// 従来の書き方
public class Main {
    public static void main(String[] args) {
        // Hello World! ('~~')
        System.out.println("Hello, Java!");

        // String
        String name = "Momoka";
        int age = 26;
        System.out.println(name);
        System.out.println(age);
        System.out.println("My name is " + name + " and I am " + age + " years old" );


        // integer and double
        int price = 12;
        int  quantity = 3;
        int total = price * quantity;
        System.out.println(total);
        System.out.println("The total is " + total);

        int bills = 10;
        int people = 4;
        System.out.println(bills/people);  // 2
        System.out.println(bills % people);   // 2
        System.out.println((double) bills / people); //  2.5


        // char
        char grade = 'A'; // SINGLE QUOTES

        // if
        int score = 80;
        if(score >= 50){
            System.out.println("Pass");
        }else{
            System.out.println("Failed");
        }

        // for
        for (int number = 1; number <= 3; number++){
            System.out.println(number);
        }
    }
}



