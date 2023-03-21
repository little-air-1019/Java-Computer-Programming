public abstract class Collection
{
    // Variables
    String title;
    String author;
    String publisher;
    String identifyNumber;
    String classifyNumber;
    String borrower;
    boolean ifBorrowed;

    // Constructor
    Collection (String title, String author, String publisher, String identifyNumber, String classifyNumber)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.identifyNumber = identifyNumber;
        this.classifyNumber = classifyNumber;
        this.ifBorrowed = false;
    }

    // Method
    public abstract void listCollectionInformation();
}

class Book extends Collection
{
    // Constructor
    Book (String title, String author, String publisher, String identifyNumber, String classifyNumber)
    {
        super(title, author, publisher, identifyNumber, classifyNumber);
    }

    // Method
    public void listCollectionInformation()
    {
        System.out.println("================================================");
        System.out.printf("題名 / %s\n", title);
        System.out.printf("作者 / %s\n", author);
        System.out.printf("出版社 / %s\n", publisher);
        System.out.printf("識別號 / %s\n", identifyNumber);
        System.out.printf("分類號 / %s\n", classifyNumber);
        System.out.println("館藏類型 / 書籍");
        if (this.ifBorrowed)
            System.out.println("館藏狀態 / 已借出");
        else
            System.out.println("館藏狀態 / 架上");
        System.out.println("================================================");
    }
}

class Magazine extends Collection
{
    // Variables
    private String year;
    private String issue;

    // Constructor
    Magazine (String title, String author, String publisher, String identifyNumber, String classifyNumber, String year, String issue)
    {
        super(title, author, publisher, identifyNumber, classifyNumber);
        this.year = year;
        this.issue = issue;
    }

    // Method
    public void listCollectionInformation()
    {
        System.out.println("================================================");
        System.out.printf("題名 / %s\n", title);
        System.out.printf("作者 / %s\n", author);
        System.out.printf("出版社 / %s\n", publisher);
        System.out.printf("期數 / %s 年第 %s 期\n", year, issue);
        System.out.printf("識別號 / %s\n", identifyNumber);
        System.out.printf("分類號 / %s\n", classifyNumber);
        System.out.println("館藏類型 / 雜誌");
        if (this.ifBorrowed)
            System.out.println("館藏狀態 / 已借出");
        else
            System.out.println("館藏狀態 / 架上");
        System.out.println("================================================");
    }
}

class Multimedia extends Collection
{
    // Variables
    private String mediaType;
    private String length;

    // Constructor
    Multimedia (String title, String author, String publisher, String identifyNumber, String classifyNumber, String mediaType, String length)
    {
        super(title, author, publisher, identifyNumber, classifyNumber);
        this.mediaType = mediaType;
        this.length = length;
    }

    // Method
    public void listCollectionInformation()
    {
        System.out.println("================================================");
        System.out.printf("標題 / %s\n", title);
        System.out.printf("作者 / %s\n", author);
        System.out.printf("出版社 / %s\n", publisher);        
        System.out.printf("識別號 / %s\n", identifyNumber);
        System.out.printf("分類號 / %s\n", classifyNumber);
        System.out.println("館藏類型 / 多媒體影音");
        System.out.printf("格式 / %s\n", mediaType);
        System.out.printf("長度（分鐘） / %s\n", length);
        if (this.ifBorrowed)
            System.out.println("館藏狀態 / 已借出");
        else
            System.out.println("館藏狀態 / 架上");
        System.out.println("================================================");
    }
}