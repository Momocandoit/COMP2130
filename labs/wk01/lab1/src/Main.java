import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        // the program start here!

        // IPO ---> Input ---> process ---> output
        // Input and Outputs are in form of string

        boolean e = true;

        // -------------------- string系　--------------------
        String name = "Momoka";
        // 新しいString objectを作る書き方 & できること
        String n = new String("Momoka");
        System.out.println(n);                // Momoka
        System.out.println(n.length());       // 6
        System.out.println(n.toUpperCase());  // MOMOKA
        // How to compare
        // 1) To compare the contents of strings
        System.out.println(name.equals(n));
        // 2) To check whether the references point to the same object (同じオブジェクトを指しているか)
        System.out.println(name == n);


        // -------------------- numeric系 --------------------
        // a++ and ++b の違い
        // a++は値を使って方増やす。++bは使う前に増やす。
        int a = 5;
        int b = 7;
        double c = 5.6;
        System.out.println("the result is " + a++ + ++b);  // 58
        // System.out.println("The result is " + (a++ + ++b)); とすればconcatenationではなく足し算になる
        System.out.println(a);                             // 6

        // printf: You specify where values should appear and how they should be formatted(表示形式を指定する)
        // %dはa, %dはb, %.2fはcと対応している。
        // %d: Displays an integer | %.2f: Displays two decimal places | \nはnew line.
        System.out.printf("The numbers are %d, %d, %.2f\n", a, b, c); // The numbers are 6, 8, 5.60 って表示される

        // Converting string into an integer
        System.out.println("Enter the first integer :");     // まずuser inputを促す
        Scanner inputInteger = new Scanner(System.in);       // Creates a Scanner object that reads console input and makes it accessible through the variable inputInteger
        // "10"として読み取られる　→ integerの10に変換　→　n1にstored
        int n1 = Integer.parseInt(inputInteger.nextLine());

        // こう書いても良い
        // String enteredText = inputInteger.nextLine();
        // int n1 = Integer.parseInt(enteredText);

        // Each variable has a dedicated class for all the methods you may need
        System.out.println("Enter the second and third number: ");
        int n2 = inputInteger.nextInt();
        int n3 = inputInteger.nextInt();
        System.out.println(n1 + n2 + n3);



        // -------------------- reading系 --------------------
        // Creating a Scanner object for reading input
        System.out.println("Enter your first name and last name:");
        Scanner sc = new Scanner(System.in);
        // String firstName = sc.next();    reads one token separated by whitespace(空白で区切られた入力を一つ読み取る)
        // String lastName = sc.next();
        String fullName = sc.nextLine(); // reads a line of input as a string(1行分の入力を文字列として読み取る)
        // System.out.println("Your full name is " + firstName + " " + lastName);
        System.out.println("Welcome " + fullName);


        // ================================
        // collect three integers find the sum and avg of those 3 and
        // show the biggest value contain in any of those numbers


        // Creating 1 Scanner for reading input, then use it 3 times to store 3 integers in separate variables
        // Step 1: Create a Scanner / 入力を読む道具を用意する
        Scanner userInput3Integers = new Scanner(System.in);

        // Step 2: Prompt user to input
        System.out.println("Enter 3 integers: ");

        // Step 3: 一つずつintegerを読み取って、variableに入れる
        int num1 = userInput3Integers.nextInt();
        int num2 = userInput3Integers.nextInt();
        int num3 = userInput3Integers.nextInt();

        int sum = num1 + num2 + num3;
        double avg = sum / 3.0;
        System.out.printf("the sum is %d and the avg is %.2f \n", sum, avg);
        if (num1 > num2 && num1 > num3)
        {System.out.println("the biggest number is " + num1);}
        else if(num2 > num3)
        {
            {System.out.println("the biggest number is " + num2);}
        }
        else{
            {System.out.println("the biggest number is " + num3);}
        }
    }
}