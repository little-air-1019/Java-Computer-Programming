import java.util.ArrayList;
import java.util.Scanner;

public class B08409045
{
    /*
     * Librarians
     * private static Librarian Adam = new Librarian("Adam", "FarAwayLIB", "SoFar1111");
     * private static Librarian Eva = new Librarian("Eva", "FarFarAwayLIB", "SoFar2222");
     * private static Librarian Helen = new Librarian("Helen", "FarFarFarAwayLIB", "SoFar3333");
     */
    // Readers
    private static ArrayList<Reader> Readers = new ArrayList<Reader>();
    // Menu 
    private static Menu menu = new Menu();
    // LogIn
    private static LogIn logIn = new LogIn();
    private static boolean if_librarian_logIn = false;
    // Collections
    private static ArrayList<Collection> Collections = new ArrayList<Collection>();
    // Scanner
    static Scanner sc = new Scanner(System.in);

    // 主程式
    public static void main(String[] args)
    {
        String input;
        
        // 首頁
        Homepage:
            do
            {
                // 印出首頁
                if (if_librarian_logIn != true)
                    menu.printMenu1();
                else
                    menu.printMenu2();
                // 輸入功能編號
                input = sc.nextLine();
                if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5"))
                {
                    // 尚未登入
                    while (if_librarian_logIn != true)
                    {
                        if_librarian_logIn = logIn.librarian_logIn();
                        if (if_librarian_logIn)
                            break;
                        System.out.println("帳號密碼錯誤!是否重新輸入? Y/N");
                        String YorN = sc.nextLine();
                        if (YorN.equals("Y"))
                            continue;
                        else
                            continue Homepage;
                    }
                    // 已登入
                    if (if_librarian_logIn)
                    {
                        // 館藏登錄
                        if (input.equals("1"))
                        {
                            addCollections();
                            continue Homepage;
                        }

                        // 借閱證註冊
                        else if (input.equals("2"))
                        {
                            register();
                            continue Homepage;
                        }

                        // 借閱
                        else if (input.equals("3"))
                        {
                            borrow();
                            continue Homepage;
                        }

                        // 歸還
                        else if (input.equals("4"))
                        {
                            returnCo();
                            continue Homepage;
                        }

                        // 查詢讀者狀態
                        else if (input.equals("5"))
                        {
                            searchRDinfo();
                            continue Homepage;
                        }

                    }
                    break;
                }

                // 查詢館藏狀態
                else if (input.equals("6"))
                {
                    searchCOinfo();
                    continue;
                }

                // 登出
                else if (input.equals("7"))
                {    
                    System.out.printf("%s已登出\n\n", logIn.logIn_user);
                    if_librarian_logIn = false;
                    logIn.logIn_user = "";
                    continue;
                }
                System.out.println("系統執行結束, 再見!");
            } while (input.equals("exit") != true);
    sc.close();
    }

    // 館藏登錄
    static void addCollections()
    {
        System.out.println("***開始進行館藏登錄***");        
        String YorN = "Y";
        while (YorN.equals("Y"))
        {
            System.out.println("館藏類型： 輸入 1 為書籍 / 輸入 2 為雜誌 / 輸入 3 為多媒體");
            String type = sc.nextLine();
            if (type.equals("1"))
            {
                System.out.print("題名：");
                String title = sc.nextLine();
                System.out.print("作者：");
                String author = sc.nextLine();
                System.out.print("出版社：");
                String publisher = sc.nextLine();
                System.out.print("識別號：");
                String identifyNumber = sc.nextLine();
                System.out.print("分類號：");
                String classifyNumber = sc.nextLine();
                Collections.add(new Book(title, author, publisher, identifyNumber, classifyNumber));
            }
            else if (type.equals("2"))
            {
                System.out.print("題名：");
                String title = sc.nextLine();
                System.out.print("作者：");
                String author = sc.nextLine();
                System.out.print("出版社：");
                String publisher = sc.nextLine();
                System.out.print("年份：");
                String year = sc.nextLine();
                System.out.print("期數：");
                String issue = sc.nextLine();
                System.out.print("識別號：");
                String identifyNumber = sc.nextLine();
                System.out.print("分類號：");
                String classifyNumber = sc.nextLine();
                Collections.add(new Magazine(title, author, publisher, identifyNumber, classifyNumber, year, issue));
            }
            else if (type.equals("3"))
            {
                System.out.print("題名：");
                String title = sc.nextLine();
                System.out.print("作者：");
                String author = sc.nextLine();
                System.out.print("出版社：");
                String publisher = sc.nextLine();
                System.out.print("多媒體類型：");
                String mediaType = sc.nextLine();
                System.out.print("長度（分鐘）：");
                String length = sc.nextLine();
                System.out.print("識別號：");
                String identifyNumber = sc.nextLine();
                System.out.print("分類號：");
                String classifyNumber = sc.nextLine();
                Collections.add(new Multimedia(title, author, publisher, identifyNumber, classifyNumber, mediaType, length));
            }
            System.out.println("館藏新增完成！");
            System.out.println("是否繼續新增? Y/N");
            YorN = sc.nextLine();
            if (YorN.equals("N"))
                return;
        }
    }

    // 借閱證註冊
    static void register()
    {
        System.out.println("***開始進行借閱證註冊***");
        System.out.print("輸入申請人姓名: ");
        String name = sc.nextLine();
        System.out.print("輸入申請人出生年月日(YYYY/MM/DD): ");
        String birthday = sc.nextLine();
        Reader aReader = new Reader(name, birthday);
        Readers.add(aReader);
        System.out.println("借閱證申請完成!");
        System.out.println("借閱證號碼為: " + aReader.getCardNumber());
    }

    // 借閱
    static void borrow()
    {
        System.out.println("***開始進行館藏借閱***");
        boolean rdNotFound = true;
        while (rdNotFound)
        {
            System.out.print("輸入借書證編號: ");
            String cardNumber = sc.nextLine();
            for (Reader rd: Readers)
            {
                if (rd.getCardNumber().equals(cardNumber))
                {
                    rdNotFound = false;
                    // 若不含本次借閱，未歸還書籍數已達 5 本，則取消本次借閱
                    if (rd.borrowCnt >= 5)
                    {
                        System.out.println("已超出借閱本數限制！請歸還後再行借閱");
                        return; // go2Homepage
                    }
                    // 輸入分類號
                    String YorN = "Y";
                    while (YorN.equals("Y"))
                    {
                        boolean coNotFound = true;
                        System.out.print("請輸入欲借閱館藏的分類號: ");
                        String classifyNumber = sc.nextLine();
                        // 判定此館藏是否存在圖書館
                        for (Collection co: Collections)
                        {
                            if (co.classifyNumber.equals(classifyNumber))
                            {
                                coNotFound = false;
                                if (co.ifBorrowed)
                                {
                                    System.out.println("此館藏已借出! 繼續借閱?");
                                    YorN = sc.nextLine();
                                    if (YorN.equals("Y"))
                                        break;
                                    else
                                        return;
                                }
                                rd.borrowCnt += 1;
                                rd.borrowedCoList.add(co.title);
                                co.ifBorrowed = true;
                                co.borrower = rd.getName();
                                System.out.printf("借閱成功! %s (%s) 已借了 %d 本書籍\n", rd.getName(), rd.getCardNumber(), rd.borrowCnt);
                                rd.printBorrowedCo();  // 印出借書證已借閱的所有館藏標題
                                System.out.println("繼續借閱? Y/N");
                                YorN = sc.nextLine();
                                if (YorN.equals("Y"))
                                    continue;
                                else
                                    return;
                            }
                        }
                        // 若系統查無此分類號
                        if (coNotFound)
                        {
                            System.out.println("分類號輸入錯誤! 繼續借閱? Y/N");
                            YorN = sc.nextLine();
                            if (YorN.equals("N"))
                                return;
                            else if (rd.borrowCnt >= 5)
                            {
                                System.out.println("已超出借閱本數限制！請歸還後再行借閱");
                                return;
                            }
                        }
                    }
                    return;
                }
            }
            // 若系統中查無此借書證編號
            if (rdNotFound)
            {
                System.out.println("借書證編號輸入錯誤!重新輸入? Y/N");
                String YorN = sc.nextLine();
                if (YorN.equals("N"))
                    return;
            }
        }
    }

    // 歸還
    static void returnCo()
    {
        String YorN = "Y";
        while (YorN.equals("Y"))
        {
            System.out.print("請輸入館藏分類號: ");
            String classifyNumber = sc.nextLine();
            boolean numNotFound = true;
            for (Collection co: Collections)
            {
                if (co.classifyNumber.equals(classifyNumber))
                {
                    numNotFound = false;
                    System.out.printf("%s, %s 歸還成功! 繼續歸還? Y/N", co.borrower, co.title);
                    for (Reader rd: Readers)
                    {
                        if (rd.getName().equals(co.borrower))
                        {
                            rd.borrowCnt -= 1;
                            rd.borrowedCoList.remove(co.title);
                        }
                    }
                    co.borrower = "";
                    co.ifBorrowed = false;
                    YorN = sc.nextLine();
                    if (YorN.equals("N"))
                        return;
                    else
                        break;
                }
            }
            if (numNotFound)
            {
                System.out.println("分類號輸入錯誤!");
                System.out.println("繼續歸還? Y/N");
                YorN = sc.nextLine();
                if (YorN.equals("N"))
                    return;
            }
        }
    }

    // 查詢讀者狀態
    static void searchRDinfo()
    {
        String YorN = "Y";
        while (YorN.equals("Y"))
        {
            boolean cdNotFound = true;
            System.out.print("請輸入借閱證號: ");
            String cardNumber = sc.nextLine();
            for (Reader rd: Readers)
            {
                if (rd.getCardNumber().equals(cardNumber))
                {
                    cdNotFound = false;
                    rd.printInfo();
                    System.out.println("繼續查詢?");
                    YorN = sc.nextLine();
                    if (YorN.equals("N"))
                        return;
                    else
                        break;
                }
            }
            if (cdNotFound)
            {
                System.out.println("查無此讀者! 繼續查詢? Y/N");
                YorN = sc.nextLine();
                if (YorN.equals("N"))
                    return;
            }
        }
    }

    // 查詢館藏狀態
    static void searchCOinfo()
    {
        String YorN = "Y";
        while (YorN.equals("Y"))
        {
            System.out.println("***開始進行館藏查詢***");
            System.out.println("輸入 1 以館藏標題查詢\n輸入 2 以作者查詢\n輸入 3 以識別號查詢");
            String mode;
            mode = sc.nextLine();
            if (mode.equals("1"))
            {
                System.out.print("請輸入館藏標題：");
                String title = sc.nextLine();
                for (Collection co: Collections)
                {
                    if (co.title.equals(title))
                    {
                        co.listCollectionInformation();
                        break;
                    }
                }
            }
            else if (mode.equals("2"))
            {
                System.out.print("請輸入作者姓名：");
                String author = sc.nextLine();
                for (Collection co: Collections)
                {
                    if (co.author.equals(author))
                    {
                        co.listCollectionInformation();
                        break;
                    }
                }
            }
            else if (mode.equals("3"))
            {
                System.out.print("請輸入館藏識別號：");
                String identifyNumber = sc.nextLine();
                for (Collection co: Collections)
                {
                    if (co.identifyNumber.equals(identifyNumber))
                    {
                        co.listCollectionInformation();
                        break;
                    }
                }
            }
            System.out.println("繼續查詢? Y/N");
            YorN = sc.nextLine();
            if (YorN.equals("N"))
                return;
        }
    }
}
