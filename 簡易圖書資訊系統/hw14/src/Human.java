import java.util.ArrayList;

class Librarian
{
    // Variables
    String name;
    String account;
    String password;

    // Constructor
    Librarian () {};

    Librarian (String name, String account, String password)
    {
        this.name = name;
        this.account = account;
        this.password = password;
    }
}

class Reader
{
    // Variables
    private String name;
    private String birthday;
    private String cardNumber;
    public int borrowCnt;
    public ArrayList<String> borrowedCoList;
    private static int readerCnt = 0;

    // Constructor
    Reader (String name, String birthday)
    {
        readerCnt++;
        this.name = name;
        this.birthday = birthday;
        this.borrowedCoList = new ArrayList<String>();
        // 編號 = 出生月分 + 日期 + 系統累積的註冊人數
        String temp[] = birthday.split("/");
        this.cardNumber = temp[1] + temp[2] + readerCnt;
        this.borrowCnt = 0;
    }

    // Method
    public String getName()
    {
        return name;
    }
    public String getCardNumber()
    {
        return cardNumber;
    }

    public void printBorrowedCo()
    {
        System.out.print("已借閱館藏: ");
        for (int i = 0; i < borrowCnt - 1; i++)
        {
            System.out.print(borrowedCoList.get(i) + ",");
        }
        System.out.println(borrowedCoList.get(borrowCnt - 1));
    }

    public void printInfo()
    {
        System.out.println("姓名 / " + name);
        System.out.println("出生年月日 / " + birthday);
        if (borrowCnt == 0)
            System.out.println("已借閱館藏 / 尚未借出任何館藏!");
        else
        {
            this.printBorrowedCo();
        }
    }

}