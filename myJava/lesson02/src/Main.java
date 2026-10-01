public class Main{
    public static void main (String[] args){

        // --------  lesson 02: 基礎から復習  ------------
        System.out.println("Hello World!");
        System.out.println("I am Momo and I am learning Java");


        // Constant(定数) : the value cannot be reassigned later. Constant names use uppercase with underscores between
        final int DAYS_IN_WEEK = 7;
        System.out.println(DAYS_IN_WEEK);
        // Day_In_Week = 8; はできない。

        final int MAX_STUDENTS = 30;

        // Arithmetic operations
        double book = 4.50;
        int numberOfBooks = 2;
        double total = book * numberOfBooks;
        System.out.println("The total is " + total + " dollars");

        // integer and decimal
        System.out.println(9/2);     // 4
        System.out.println(9.0/2);   // 4.5

        // Character data type
        char grade = 'A';
        String name = "Momoka";
        System.out.println(grade);
        System.out.println((int) grade);  // 65
        System.out.println('A' + 1);      // 66
        System.out.println('A' + 'B');    // 131
        System.out.println("A" + "B");    // AB

        System.out.println(3 + 2);                 // 5
        System.out.println("Total is " + 3 + 2);   // Total is 32
        System.out.println("Total is " + (3 + 2)); // Total is 5

        // Escape sequence
        System.out.println("Hello\nMomoka");    // \n : new line
        System.out.println("This is a double quotation mark: \" ");
        System.out.println("Name:\tMomoka\nGrade:\tA");

        // Other numeric types
        /*
        * byte    −128 ～ 127
        * short   −32,768 ～ 32,767
        * int     −2,147,483,648 ～ 2,147,483,647
        * long    −9,223,372,036,854,775,808 ～ 9,223,372,036,854,775,807
        * */
        long population = 8_000_000_000L;   // Underscores make it easier to read. They don't affect the value.
        System.out.println(population);
        float height = 1.56F;

        // Type conversion
        // 1-1) int ---> double
        int apples = 5;
        double value = apples;      // Conversion from int to double happens automatically
        System.out.println(value);

        // 1-2)
        int someTotal = 5;
        int somePeople = 2;
        double average_pre = (double) someTotal/somePeople;       //割る前に変換
        double average_after = (double) (someTotal/somePeople);   //割った後に変換
        System.out.println("Average for 1-2) " + average_pre);    // 2.5
        System.out.println("Average for 1-2) " + average_after);  // 2.0

        // 2) double  ---> int
        double someValue = 4.9;
        int number =(int) someValue;
        System.out.println(number); // 4 (It doesn't round the number.)





    }
}