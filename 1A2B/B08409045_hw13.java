import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B08409045_hw13
{
    // 檢查數字有沒有重複
    public static boolean checkDuplicate(String answer)
    {
        Pattern pattern = Pattern.compile("^(?!.*(.).*\\1)\\d{4}");
        Matcher matcher = pattern.matcher(answer);
        if (matcher.matches())
            return false;
        else
            return true;
    }

    // 產生隨機四位整數
    public static String generateNum()
    {
        Random rd = new Random();
        int answer;
        String answerValue;
        do
        {
           answer = rd.nextInt(1000, 10000);
           answerValue = Integer.toString(answer);
        }
        while(checkDuplicate(answerValue) == true);

        return answerValue;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int mode = 0, gameCnt = 0, correctCnt = 0, guessCnt = 0, failCnt = 0;
        int[] history = {correctCnt, guessCnt, failCnt};
        String output;
        do
        {
          System.out.println("===Welcome to the Guessing Game===");
          System.out.println("1: 開始遊戲");
          System.out.println("2: 過去遊戲歷史記錄");
          System.out.println("3: 結束遊戲");
          System.out.println("==============================");
          System.out.println("請輸入您的選擇(1, 2, or 3):");
          try
          {
            mode = sc.nextInt();
          }
          catch(Exception e)
          {
            System.out.println("輸入格式錯誤!");
          }
          
          sc.nextLine();
          if (mode == 1)
          {
            gameCnt++;
            String answer = generateNum();
            System.out.println("本次猜數字的答案是" + answer);
            mode1(sc, answer, history);
          }
          else if (mode == 2)
          {
            System.out.println("總共遊戲次數: " + gameCnt);
            if (history[0] > 0)
            {
                output = String.format("總共猜中次數: %d, 平均%s回合猜中", history[0], String.format("%.2f", (float)history[1] / history[0]));
                System.out.println(output);
            }
            else
            {
                System.out.println("總共猜中次數： 0");
            }
            System.out.println("總共失敗次數： " + history[2]);
            System.out.println("總共勝率為: " + String.format("%.2f", (float)history[0] / gameCnt));
          }
        } while(mode != 3);
        System.out.println("遊戲結束, Bye Bye!");
        sc.close();
    }

    // 玩遊戲
    public static void mode1(Scanner sc, String answer, int[] history)
    {
        int count = 1;
        String[] cmpResult = new String[10];
        while(count <= 10)
        {
            String output = String.format("第%d次猜答: 請輸入四位數字, 數字不重複:", count);
            System.out.println(output);
            // 第8次猜題給提示
            if (count == 8)
            {
                output = String.format("答案提示: X%sX%s", answer.charAt(1), answer.charAt(3));
                System.out.println(output);
            }
            String guess = sc.nextLine();
            if (checkDuplicate(guess))
            {
                System.out.println("輸入數字必須是4位不重複的數字!\n請再輸入一次");
                continue;
            }
            if (guess.equals(answer))
            {
                output = String.format("%s, 答對了! Bingo!!!", guess);
                System.out.println(output);
                history[0] += 1;
                history[1] += count;
                return;
            }
            else
            {
                int a = 0, b = 0;
                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        if (guess.charAt(i) == answer.charAt(j))
                        {
                            if (i == j)
                            {
                                a++;
                            }
                            else
                            {
                                b++;
                            }
                        }
                    }
                }
                System.out.println();
                System.out.println();
                System.out.println("比較結果為:");
                String result = String.format("%s, %dA%dB", guess, a, b);
                cmpResult[count - 1] = result;
                for (int i = 0; i < count; i++)
                {
                    System.out.println(cmpResult[i]);
                }
                System.out.println("--------------------------");
                count++;
            }
        }
        // 猜了十次後仍未解出
        System.out.println("已猜答超過10次, 失敗! 答案為:");
        System.out.println(answer);
        history[2] += 1;
    }
}