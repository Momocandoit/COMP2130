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
        /*
        int a = 5;
        int b = 7;
        double c = 5.6;
        boolean e = true;
        String name = "hesam akbari";
        String n = new String("Hesam Akbari");
        System.out.println("the result is " + a++ + ++b);
        System.out.println(a);

        System.out.printf("the numbers are %d,  %d, %.2f\n", a, b, c);
        //====================================================================//
        // I want to collect someone full name!
        System.out.println("Enter your first name and last name:");
        Scanner sc = new Scanner(System.in);
        //String fname = sc.next();
        //String lname = sc.next();
        String fullName = sc.nextLine();

       // System.out.println("your full name is " + fname + " " + lname);
        System.out.println(fullName);
        System.out.println("Enter the first integer :");
        // Each variable has a dedicated class for all the methods you may need
        int n1 = Integer.parseInt(sc.nextLine());
        System.out.println("enter the second and third: ");
        int n2 = sc.nextInt();
        int n3 = sc.nextInt();
        System.out.println(n1+n2+n3);

        // ================================
        // collect three integers find the sum and avg of those 3 and
        // show the biggest value contain in any of those numbers
*/
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 3 integers: ");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();
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