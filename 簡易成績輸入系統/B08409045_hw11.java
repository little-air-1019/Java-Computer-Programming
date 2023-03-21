import java.util.Scanner;

public class B08409045_hw11
{
    public static void main(String[] args) throws Exception
    {
        int[] students = new int[10];
        int studentCnt = 0;
        // Step 1
        System.out.println("Welcome to use this Java application!");
        Scanner sc = new Scanner(System.in);
        boolean endProgram = false;
        while(endProgram == false)
        {
            // Step 2
            System.out.println("Please input a student's score:");
            int score = sc.nextInt();
            if (score < 0 || score > 100)
            {
                System.out.println("Wrong score!");
                continue;
            }
            else
            {
                students[studentCnt] = score;
                studentCnt++;
            }
            // Step 3
            boolean inputAgain = false;
            while(studentCnt <= 10)
            {
                System.out.printf("You have input %d score information\n", studentCnt);
                // Step 4
                System.out.println("Continue? Press '1', 'Y', or 'y' for Yes, and '2', 'N', or 'n' for No:");
                String mode = sc.next();
                if (studentCnt == 10)
                {
                    System.out.println("You have completed the score input!");
                    break;
                }
                if (mode.equals("1") || mode.equals("Y") || mode.equals("y"))
                {
                    inputAgain = true;
                    break;
                }
                else if (mode.equals("2") || mode.equals("N")|| mode.equals("n"))
                {
                    break;
                }
                else
                {
                    System.out.println("Wrong Information!");
                }
            }
            if (inputAgain == true)
                continue;
            // Step 5
            while(true)
            {
                System.out.println("What operation do you want to execute?");
                System.out.println("Press '1' to list all scores;");
                System.out.println("Press '2' to calculate the average of scores;");
                System.out.println("Press '3' to sort and list the scores (from minimum to maximum);");
                System.out.println("Press '4' to continue the score input;");
                System.out.println("Press '5' to quit this Java application.");
                int mode = sc.nextInt();
                // Step 6
                if (mode == 1)
                {
                    for (int i = 0; i < studentCnt - 1; i++)
                    {
                        System.out.print(students[i] + " ");
                    }
                    System.out.println(students[studentCnt - 1]);
                    continue;
                }
                // Step 7
                if (mode == 2)
                {
                    double avg = 0;
                    for (int i = 0; i < studentCnt; i++)
                        avg += students[i];
                    avg = avg / studentCnt;
                    System.out.printf("%.2f\n" ,avg);
                    continue;
                }
                // Step 8
                if (mode == 3)
                {
                    // Insertion sort
                    int[] sortedStudents = new int[10];
                    for (int i = 0; i < studentCnt; i++)
                        sortedStudents[i] = students[i];
                    for (int i = 0; i < studentCnt - 1; i++)
                    {
                        for (int j = 0; j < studentCnt - i - 1; j++)
                        {
                            if (sortedStudents[j] > sortedStudents[j + 1])
                            {
                                int temp = sortedStudents[j];
                                sortedStudents[j] = sortedStudents[j + 1];
                                sortedStudents[j + 1] = temp;
                            }
                        }
                    }
                    for (int i = 0; i < studentCnt - 1; i++)
                        System.out.print(sortedStudents[i] + " ");
                    System.out.println(sortedStudents[studentCnt - 1]);
                    continue;
                }
                // Step 9
                if (mode == 4)
                {
                    if (studentCnt < 10)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("You have completed the score input!");
                        continue;
                    }
                }
                // Step 10
                if (mode == 5)
                {
                    System.out.println("Thank you for using this Java application! Goodbye!");
                    endProgram = true;
                    break;
                }
            }
        }
        sc.close();
    }
}
