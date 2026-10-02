import java.util.Scanner;

public class Main {


    // --------------- Overall of Data Structure/ データ構造の全体像 ---------------
    // Collection or a bundle of data which may have correlation with each other.(object)
    // 関連するデータをまとめて扱う　(names, grades, etc.)
    // 1) String: Collection of characters in sequence --> Immutable (read only/変更できない)
    //            Stringは文字列が順番に並んだ文字列。作成された文字列の内容は変更できない(別の文字列は代入できる)。
    // 2) Arrays: Collection of same data type in sequence --> Mutable(変更できる)
    // 　　　　　　 fix size(作成した配列の要素数は変更できない) --> Not dynamic
    //            Variety of Arrays: Multi- Dimensional Arrays, ArrayList(要素を追加削除できる),
    //                               array of strings , array of arrays, List, Queue, Hashmaps, etc.
    // 3) Dictionaries, JSON: These are not in sequence and keep them in pairs --> (key:value)

    static void main() {

        // Creating an array
        int [] numbers = new int[3]; // 作成したarrayはobject
        float des [] = new float[3]; // Java supports C-style array declaration (variableName [] = new ...)

        // Printing out elements
        System.out.println(numbers[0]);  // 0が表示される。 A newly created int array contains zero
        System.out.println(des[0]);      // 0.0

        // Two-dimensional arrays
        // C#だと書き方は２種類。[][] or [,]
        // Why 2 square brackets?: They let you access a value using a row index and a column index.
        //                         1st [] is row and 2nd [] is column (firstTwoDim[2][3]はrow index 2, column index 3)
        // Rectangle, matrix, tableとしてデータを見られる
        // Hard-coding: Writing the values into the code before running the program/ 使う値を直接コードの中に書いておくこと
        int [][] firstTwoDim = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };

        // Parallel Arrays and 2D
        // Tracker system for java students marks breakdown
        // Same sizeである必要がある
        String [] content = {"Midterm", "Final", "Quizzes", "Assignment 1", "Assignment 2", "Lab test 1", "Lab test 2"};
        float  [] weight =  {0.2f, 0.25f, 0.1f, 0.1f, 0.15f, 0.1f, 0.1f};

        // These lines declare 3 variables, not create the arrays yet.
        long [] studentID ;
        String [] studentName ;
        double [][] studentMarks;

        // Collect the info from the user
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many student are registered in Java class? ");
        int nStudent = keyboard.nextInt();

        // Create the actual arrays. nStudent前のコードでuserが入力したnumber of students
        studentID = new long[nStudent];
        studentName = new String[nStudent];
        studentMarks = new double[nStudent][content.length]; // content.lengthはさっき入力したline 43あたりのやつのこと

        for (int i = 0; i < nStudent; i++) {
            System.out.printf("Enter student # %d name and ID", (i+1)); // &dに(i+1)が入る

            studentName[i] = keyboard.next();
            studentID[i] = keyboard.nextLong();

            for (int j = 0; j <content.length ; j++) {

                System.out.printf("\n Enter the mark for %s: ", content[j]);
                studentMarks[i][j] = keyboard.nextDouble();
            }
        }


        // Print all the information
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
