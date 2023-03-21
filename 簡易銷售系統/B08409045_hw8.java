import java.util.Scanner;

public class B08409045_hw8
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String inpStr = sc.nextLine();
        String[] inp = inpStr.split(",");
        int itemCnt = Integer.parseInt(inp[0]);
        int dayCnt = Integer.parseInt(inp[1]);
        // 輸入價錢陣列
        inpStr = sc.nextLine();
        inp = inpStr.split(",");
        int price[] = new int[itemCnt];
        for (int i = 0; i < itemCnt; i++)
        {
            price[i] = Integer.parseInt(inp[i]);
        }
        // 輸入銷售資料
        int[][] items = new int[itemCnt][dayCnt];
        for (int i = 0; i < itemCnt; i++)
        {
            inpStr = sc.nextLine();
            inp = inpStr.split(",");
            for (int j = 0; j < dayCnt; j++)
            {
                items[i][j] = Integer.parseInt(inp[j]);
            }
        }
        // 輸入想分析的項目
        inpStr = sc.nextLine();
        inp = inpStr.split(",");
        int cmd[] = new int[inp.length];
        for (int i = 0; i < inp.length; i++)
        {
            cmd[i] = Integer.parseInt(inp[i]);
        }
        sc.close();
        // 依據指令操作
        int[] dailySales = daily_sales(itemCnt, dayCnt, items, price);
        int[] goodsSales = goods_sales(itemCnt, dayCnt, items, price);
        int sales = sales_sum(goodsSales);
        int dailyAvg = daily_avg(sales, dayCnt);
        int[] goodsAvg = goods_avg(dayCnt, goodsSales);
        int bestDay = best_day(itemCnt, dayCnt, items, price);
        for (int i = 0; i < cmd.length; i++)
        {
            if (cmd[i] == 1)
            {
                System.out.println(sales);
            }
            else if (cmd[i] == 2)
            {
                for (int j = 0; j < dayCnt - 1; j++)
                {
                    System.out.print(dailySales[j] + ",");
                }
                System.out.println(dailySales[dayCnt - 1]);
            }
            else if (cmd[i] == 3)
            {
                System.out.println(dailyAvg);
            }
            else if (cmd[i] == 4)
            {
                for (int j = 0; j < itemCnt - 1; j++)
                {
                    System.out.print(goodsSales[j] + ",");
                }
                System.out.println(goodsSales[itemCnt - 1]);
            }
            else if (cmd[i] == 5)
            {
                for (int j = 0; j < itemCnt - 1; j++)
                {
                    System.out.print(goodsAvg[j] + ",");
                }
                System.out.println(goodsAvg[itemCnt - 1]);
            }
            else if (cmd[i] == 6)
            {
                System.out.println(bestDay);
            }
        }
    }
    // 計算所有商品所有日子合計總銷售金額(S)
    public static int sales_sum(int[] goodsSales)
    {
        int sales = 0;
        for (int i = 0; i < goodsSales.length; i++)
        {
            sales += goodsSales[i];
        }
        return sales;
    }
    // 計算每日銷售金額（𝐷1, 𝐷2, . . . , 𝐷𝑡），傳回值為一個 Array
    public static int[] daily_sales(final int itemCnT, final int dayCnt, final int[][] goods, final int[] price)
    {
        int[] dailySales = new int[dayCnt];
        for (int i = 0; i < itemCnT; i++)
        {
            for (int j = 0; j < dayCnt; j++)
            {
                dailySales[j] += goods[i][j] * price[i];
            }
        }
        return dailySales;
    }
    // 計算平均每日銷售金額（𝐴𝐷），傳回值 𝐴𝐷 為一個數值
    public static int daily_avg(int sales, final int dayCnt)
    {
        return sales / dayCnt;
    }
    // 計算各商品總銷售金額（𝐺1, 𝐺2, . . . , 𝐺𝑛），傳回值為一個 Array
    private static int[] goods_sales(final int itemCNT, final int dayCnt, final int[][] goods, final int[] price)
    {
        int[] goodsSales = new int[itemCNT]; 
        for (int i = 0; i < itemCNT; i++)
        {
            for (int j = 0; j < dayCnt; j++)
            {
                goodsSales[i] += goods[i][j];
                
            }
            goodsSales[i] *= price[i];
        }
        return goodsSales;
    }
    // 計算各項商品平均每日銷售金額（𝐴𝐺1, 𝐴𝐺2, . . . , 𝐴𝐺𝑛），傳回值為一個 Array
    public static int[] goods_avg(final int dayCnt, int[] goodsSales)
    {
        int[] goodsAvg = new int[goodsSales.length];
        for (int i = 0; i < goodsSales.length; i++)
        {
            goodsAvg[i] = goodsSales[i] / dayCnt;
        }
        return goodsAvg;
    }
    // 平均銷售金額最高為星期幾（𝑊），傳回值 𝑊 為一個數值
    public static int best_day(final int itemCnT, final int dayCnt, final int[][] goods, final int[] price)
    {
        int[] weekSales = new int[7];
        int[] weekDays = {1, 1, 1, 1, 1, 1, 1};
        for (int i = 0; i < itemCnT; i++)
        {
            for (int j = 0; j < dayCnt; j++)
            {
                switch ((j + 1) % 7)
                {
                    case 1:
                    weekSales[0] += goods[i][j] * price[i];
                    weekDays[0] += 1;
                    break;
                    
                    case 2:
                    weekSales[1] += goods[i][j] * price[i];
                    weekDays[1] += 1;
                    break;
                    
                    case 3:
                    weekSales[2] += goods[i][j] * price[i];
                    weekDays[2] += 1;
                    break;
                    
                    case 4:
                    weekSales[3] += goods[i][j] * price[i];
                    weekDays[3] += 1;
                    break;
    
                    case 5:
                    weekSales[4] += goods[i][j] * price[i];
                    weekDays[4] += 1;
                    break;
    
                    case 6:
                    weekSales[5] += goods[i][j] * price[i];
                    weekDays[5] += 1;
                    break;
    
                    case 0:
                    weekSales[6] += goods[i][j] * price[i];
                    weekDays[6] += 1;
                    break;
                }
            }
        }
        int maxSales = weekSales[0] / weekDays[0];
        int bestDay = 1;
        for (int i = 1; i < 7; i++)
        {
            if (weekSales[i] / weekDays[i] > maxSales)
            {
                maxSales = weekSales[i] / weekDays[i];
                bestDay = i + 1;
            }
        }
        return bestDay;
    }
}