//用户可添加、删除书籍，可通过此程序显示书籍并退出系统
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return "书名： " + title + ", 作者： " + author + ", 出版年份： " + year;
    }
}

class Library {
    ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equals(title)) {
                books.remove(i);
                break;
            }
        }
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("请选择操作：1. 添加书籍 2. 删除书籍 3. 显示书籍 4. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("请输入书名：");
                    String title = scanner.nextLine();
                    System.out.println("请输入作者：");
                    String author = scanner.nextLine();
                    System.out.println("请输入出版年份：");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    library.addBook(new Book(title, author, year));
                    break;
                case 2:
                    System.out.println("请输入要删除的书籍书名：");
                    String removeTitle = scanner.nextLine();
                    library.removeBook(removeTitle);
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }

        scanner.close();
    }
}