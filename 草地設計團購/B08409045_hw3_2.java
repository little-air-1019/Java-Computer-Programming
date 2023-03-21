import java.util.Scanner;

public class B08409045_hw3_2
{
    public static void main(String args[])
    {
        float pay = 0;
        int profit = 0;
        Scanner sc = new Scanner(System.in);
        int cost = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
        {
            int pi = sc.nextInt();
            int xi = sc.nextInt();
            // 20件以上：8折
            if (xi >= 20)
            {
                pay += pi * xi * 0.8;
                profit += 50 * xi;
            }
            // 10~20件
            else if (xi >= 10)
            {
                if (20 - xi <= 2)  // 湊成20件打8折
                {
                    pay += pi * 20 * 0.8;
                    profit += (50 * xi) + (pi * 0.2 * (20 - xi));
                }
                else  // 9折
                {
                    pay += pi * xi * 0.9;
                    profit += 30 * xi;
                }
            }
            // 低於10件
            else if (xi < 10)
            {
                if ((10 - xi) <= 2)
                {
                    pay += pi * 10 * 0.9;
                    profit += (30 * xi) + (pi * 0.1 * (10 - xi));
                }
                else
                {
                    pay += pi * xi;
                }
            }
        }
        sc.close();
        int pay2int = (int)pay;
        profit -= cost;
        System.out.println(pay2int + " " + profit);
    }
}
