import java.util.Scanner;

public class LogIn
{
    // Variables
    boolean logIn;
    String logIn_user;

    // Constructor
    LogIn()
    {
        logIn = false;
        logIn_user = "";
    }
    
    // Method
    boolean librarian_logIn()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("館員請先登入，才可以使用此功能！");
        System.out.print("請輸入帳號: ");
        String account = sc.nextLine();
        System.out.print("請輸入密碼: ");
        String password = sc.nextLine();
        // sc.close(); 主程式再關
        if (account.equals("FarAwayLIB") && password.equals("SoFar1111"))
        {
            System.out.println("嗨 Adam, 歡迎登入!\n");
            logIn_user = "Adam";
            return true;
        }
        else if (account.equals("FarFarAwayLIB") && password.equals("SoFar2222"))
        {
            System.out.println("嗨 Eva, 歡迎登入!\n");
            logIn_user = "Eva";
            return true;
        }
        else if (account.equals("FarFarFarAwayLIB") && password.equals("SoFar3333"))
        {
            System.out.println("嗨 Helen, 歡迎登入!\n");
            logIn_user = "Helen";
            return true;
        }
        else
        {
            return false;
        }
    }
}
