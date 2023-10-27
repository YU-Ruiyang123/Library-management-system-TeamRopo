public class Book {
    private String BookName;
    private int SerialNumber;
    private char areaNumber;
    private int BookshelfNumber;
    private String positionNumber;
    private String Type;
    public Book(){
        this.BookName="NO name";
        this.SerialNumber=00000000;
        this.areaNumber='A';
        this.BookshelfNumber=1;
        this.positionNumber="0-0000";
        this.Type="Np Type";
    }
    public Book(String BookName,int SerialNumber,char areaNumber,int BookshelfNumber,String positionNumber,String Type){
        this.BookName=BookName;
        this.SerialNumber=SerialNumber;
        this.areaNumber=areaNumber;
        this.BookshelfNumber=BookshelfNumber;
        this.positionNumber=positionNumber;
        this.Type=Type;
    }
//////////////////////////////////////////////////////////////////////
    public char getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(char areaNumber) {
        this.areaNumber = areaNumber;
    }

    public int getBookshelfNumber() {
        return BookshelfNumber;
    }

    public void setBookshelfNumber(int bookshelfNumber) {
        BookshelfNumber = bookshelfNumber;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
//////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "Book{" +
                "BookName='" + BookName + '\'' +
                ", SerialNumber=" + SerialNumber +
                ", areaNumber=" + areaNumber +
                ", BookshelfNumber=" + BookshelfNumber +
                ", positionNumber='" + positionNumber + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}

