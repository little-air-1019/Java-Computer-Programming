import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
class Board
{
    Scanner sc = new Scanner(System.in);
    public String[][] board = new String[5][5];
    // Constructor
    public Board() {};
    // Setter for board
    void setBoard(int mode)
    {
        // 1:由玩家自行手動輸入資料
        if (mode == 1)
        {
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    board[i][j] = sc.next();
                }
            }  
        }
        // 2:由系統亂數產生遊戲資料
        else if (mode == 2)
        {
            Integer[] nums = new Integer[25];
            for (int i = 0; i < 25; i++)
                nums[i] = i + 1;
            List<Integer> numsList = Arrays.asList(nums);
            Collections.shuffle(numsList);
            numsList.toArray(nums);
            
            int k = 0;
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    board[i][j] = nums[k] + "";
                    k++;
                }
            }
        }
    }
    // 印出賓果盤
    void printBoard()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (board[i][j].length() == 1)
                    System.out.print("0" + board[i][j] + " ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    // 對號
    void checkNum(int num)
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (board[i][j].equals(num + ""))
                {
                    board[i][j] = "00";
                }
            }
        }
    }
    // 幾連線
    int countLines()
    {
        int lineCnt = 0;
        // 水平線
        for (int i = 0; i < 5; i++)
        {
            int count = 0;
            for (int j = 0; j < 5; j++)
            {
                if (board[i][j].equals("00"))
                    count++;
            }
            if (count == 5)
                lineCnt++;
        }
        // 鉛直線
        for (int i = 0; i < 5; i++)
        {
            int count = 0;
            for (int j = 0; j < 5; j++)
            {
                if (board[j][i].equals("00"))
                    count++;
            }
            if (count == 5)
                lineCnt++;
        }
        // 斜線
        int count = 0;
        for (int i = 0; i < 5; i++)  // 右下斜線
        {    
            if (board[i][i].equals("00"))
                count++;
        }
        if (count == 5)
            lineCnt++;
        count = 0;
        for (int i = 4; i >= 0; i--) // 左下斜線
        {
            if (board[4 - i][i].equals("00"))
                count++;
        }
        if (count == 5)
            lineCnt++;
        
        return lineCnt;
    }
    // 玩遊戲
    boolean ifBingo(int num)
    {
        this.checkNum(num);
        int lines = this.countLines();
        if (lines >= 5)
            return true;
        else
            return false;
    }
}
public class B08409045_hw12
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception
    {
        int gameCnt = 0;
        while(true)
        {
            // 遊戲初始介面
            System.out.println("===Welcome to the Bingo Game===");
            System.out.println("1:由玩家自行手動輸入資料");
            System.out.println("2:由系統亂數產生遊戲資料");
            System.out.println("3:結束遊戲");
            System.out.println("===============================");
            if (gameCnt > 0)
                System.out.println("您已經進行過" + gameCnt + "場Bingo遊戲!");
            System.out.println();
            System.out.println("請輸入你的選擇(1, 2, or 3)");
            int mode = sc.nextInt();
            if (mode == 3)
                break;
            gameCnt++;
            Board board1 = new Board();
            Board board2 = new Board();
            // 建立Bingo盤
            if (mode == 1)
            {
                System.out.println("請輸入數列以產生賓果盤A,範圍為1~25之不重複整數,中間以空格區分");
                board1.setBoard(mode);
                System.out.println("以下為您所輸入之賓果盤A:");
                board1.printBoard();
                System.out.println();
                System.out.println("請輸入數列以產生賓果盤B,範圍為1~25之不重複整數,中間以空格區分");
                board2.setBoard(mode);
                System.out.println("以下為您所輸入之賓果盤B:");
                board2.printBoard();
                System.out.println();
            }
            else
            {
                System.out.println("系統所產生之賓果盤A:");
                board1.setBoard(mode);
                board1.printBoard();
                System.out.println();
                System.out.println("系統所產生之賓果盤B:");
                board2.setBoard(mode);
                board2.printBoard();
                System.out.println();
            }
            // 喊數字 & 玩賓果
            System.out.println("請輸入賓果叫號數列,範圍為1~25的之不重複整數,中間以空格區分:");
            int[] allGuess = new int[25];
            int winAt1 = 0, winAt2 = 0, lastNum1 = 0, lastNum2 = 0;
            for (int i = 0; i < 25; i++)
            {
                allGuess[i] = sc.nextInt();
            }
            for (int i = 0; i < 25; i++)
            {
                // 玩
                if (board1.ifBingo(allGuess[i]) == true)
                {
                    winAt1 = i + 1;
                    lastNum1 = allGuess[i];
                    break;
                }
            }
            for (int i = 0; i < 25; i++)
            {
                if (board2.ifBingo(allGuess[i]) == true)
                {
                    winAt2 = i + 1;
                    lastNum2 = allGuess[i];
                    break;
                }
            }
            // 輸出結果
            if (winAt1 > winAt2)
            {
                System.out.println("遊戲結果輸出: 賓果盤B獲勝!");
                System.out.printf("賓果盤B於第%d個數字達成五連線,最後一個數字為%d\n", winAt2, lastNum2);
                board2.printBoard();
                System.out.println();
                System.out.printf("賓果盤A於第%d個數字達成五連線,最後一個數字為%d\n", winAt1, lastNum1);
                board1.printBoard();
            }
            else
            {
                if (winAt1 < winAt2)
                    System.out.println("遊戲結果輸出: 賓果盤A獲勝!");
                else
                    System.out.println("遊戲結果輸出: 平手!");
                System.out.printf("賓果A於第%d個數字達成五連線,最後一個數字為%d\n", winAt1, lastNum1);
                board1.printBoard();
                System.out.println();
                System.out.printf("賓果盤B於第%d個數字達成五連線,最後一個數字為%d\n", winAt2, lastNum2);
                board2.printBoard();
            }
        }
    }
}