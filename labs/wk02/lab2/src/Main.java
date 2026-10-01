import java.util.Scanner;

public class Main {


    //================== Overall of data structure ================
    // ---> collection or a bundle of data which may have correlation with each other.(object)
    // 1) String => collection of characters in sequence => it's immutable (read only)
    // 2) Arrays => Collection of same data type in sequence => Mutable ---> (fix size) -> its not dynamic
    // ----------> variety of Arrays --> ArrayList, List, Queue, Hashmaps, ...
    // Multi- Dimensional Arrays
    // 3) Dictionaries, JSON --> These are not in sequence and keep them in pairs --> (key:value)

    // --->array of strings , array of arrays
    static void main() {

        int [] numbers = new int[3]; // object
        float des [] = new float[3]; // Java supports C-style array declaration number []   [] number

        System.out.println(numbers[0]);
        System.out.println(des[0]);

        // two-dimensional arrays
        // C# --> [][]   [,]
        int [][] dummy = {{2,3,4},
                {3,4,5},
                {6,7,8},
                {7,5,6}}; // hard-coding  ---> rectangle, Matrix, table


        //--------------------------------------------------------------------------//
        // parallel Arrays and 2D
        // tracker system for java students marks breakdown
        String [] content = {"Midterm", "Final", "Quizzes", "Assignment 1", "Assignment 2", "Lab test 1", "Lab test 2"};
        float  [] weight =  {0.2f, 0.25f, 0.1f, 0.1f, 0.15f, 0.1f, 0.1f};
        // parallel Arrays ---> they are the same size


        long [] studentID ;

        String [] studentName ;

        double [][] studentMarks;

        // collect the info from the user
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many student was registered in Java");
        int nStudent = keyboard.nextInt();

        studentID = new long[nStudent];
        studentName = new String[nStudent];
        studentMarks = new double[nStudent][content.length];

        for (int i = 0; i < nStudent; i++) {

            System.out.printf("Enter student # %d name and ID", (i+1));

            studentName[i] = keyboard.next();
            studentID[i] = keyboard.nextLong();

            for (int j = 0; j <content.length ; j++) {

                System.out.printf("\n Enter the mark for %s: ", content[j]);
                studentMarks[i][j] = keyboard.nextDouble();
            }
        }


        // print all the student information ----------------------------
        // what if we need to find the final marks of each student?
        // Average of the class
        System.out.println(studentMarks.length);
        double finalMark;
        double addFinal = 0;
        // go through the row
        for (int i = 0; i <studentMarks.length; i++) {
            finalMark = 0;

            System.out.printf("Student ID : %d  || student Name : %s \n", studentID[i], studentName[i]);
            for (int j = 0; j < studentMarks[i].length; j++) { // second dimension

                System.out.printf("\t%s = %.2f\n", content[j], studentMarks[i][j]);
                finalMark += studentMarks[i][j] * weight[j]; // final mark of java

            }

            System.out.println("----------------------------------");
            System.out.printf("\tFinal Mark = %.2f\n" , finalMark);
            System.out.println("----------------------------------");
            addFinal += finalMark;
        }

        System.out.printf("The average of class is %.2f\n", addFinal/studentMarks.length);


        // ------------------------------ average of each content -------------------------

        double add;
        for (int j = 0; j < content.length; j++) { // each column

            add = 0;

            for (int i = 0; i < studentName.length; i++) {

                add += studentMarks[i][j]; // [0,0] + [1,0] + [2, 0]
                //[0,1] + [1,1] + [2, 1]

            }

            System.out.printf("The average of %s = %.2f\n", content[j], (add/ studentName.length));

        }







    }
}
