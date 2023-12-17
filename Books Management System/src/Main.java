import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.InputMismatchException;

public class Main {
    private static BookManager manager = new BookManager(); //Objects used to manage books. /用于管理书籍的对象
    private static Scanner scanner = new Scanner(System.in); //A scanner used to obtain user input./用于获取用户输入的扫描器

    public static void main(String[] args) {
        manager.loadBooksFromFile(); // Load the book data when the program starts./程序启动时加载书籍数据
        while (true) {
            displayMenu();
            try {
                System.out.print("Please enter your selection: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); //Clear the newline of the input line./清除输入行的换行符

                switch (choice) {
                    case 1:
                        addBook();
                        manager.saveBooksToFile(); //After adding the book, save the data to the file to data/books.csv /添加书籍后,保存数据到文件data/books.csv
                        break;
                    case 2:
                        displayAllBooks();
                        break;
                    case 3:
                        updateBook();
                        manager.saveBooksToFile(); //After updating the book, save the data to a file to data/books.csv /更新书籍后,保存数据到文件data/books.csv
                        break;
                    case 4:
                        deleteBook();
                        manager.saveBooksToFile(); // After deleting the books,d saves the data to the file data/books.csv /删除书籍后,d保存数据到文件data/books.csv
                        break;
                    case 5:
                        searchBooks(); //Invoke the search book method /调用搜索书籍的方法
                        break;
                    case 6:
                        System.out.println("Log out");
                        return;
                    default:
                        System.out.println("Invalid selection, please re-enter！");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid selection, please re-enter！");
                scanner.nextLine(); //Clear incorrect input / 清除错误输入
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n Books Management System:");
        System.out.println("1. Add a book");
        System.out.println("2. Display all books");
        System.out.println("3. Update a book");
        System.out.println("4. Delete a book");
        System.out.println("5. Sreach for a book");
        System.out.println("6. Exit");
    }

    private static void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        while (!isValidISBN(isbn)) {
            System.out.print("Invalid ISBN, please enter the ISBN in the correct format (for example: 123456789X or 123-456-789-X123): ");
            isbn = scanner.nextLine();
        }

        manager.addBook(new Book(title, author, isbn));
        System.out.println("Book added.");
    }

    private static void displayAllBooks() {
        List<Book> books = manager.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
        }
    }

    private static boolean isValidISBN(String isbn) {
        String regex = "^(97(8|9))?\\d{9}(\\d|X)$"; // The regular expression of ISBN-10 or ISBN-13 /ISBN-10或ISBN-13的正则表达式
        // Remove dashes from ISBNs /移除ISBN中的短划线
        String isbnClean = isbn.replace("-", "");
        return Pattern.matches(regex, isbnClean);
    }


    private static void updateBook() {
        System.out.print("Enter the ISBN of the book you want to update: ");
        String isbn = scanner.nextLine();

        // Suppose we update only the title and author, but not the ISBN /假设我们只更新书名和作者，不更新ISBN
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter new author: ");
        String newAuthor = scanner.nextLine();

        // Find and update books using ISBN /使用ISBN查找并更新书籍
        manager.updateBook(isbn, new Book(newTitle, newAuthor, isbn));
        System.out.println("Books have been updated.");
    }
    private static void deleteBook() {
        System.out.print("Enter the ISBN of the book you want to delete: ");
        String isbn = scanner.nextLine();

        manager.deleteBook(isbn);
        System.out.println("Books deleted.");
    }


    // New ways to search for books /搜索书籍的新方法
    private static void searchBooks() {
        System.out.print("Enter the search keyword: ");
        String keyword = scanner.nextLine();
        List<Book> foundBooks = manager.searchBooks(keyword);
        if (foundBooks.isEmpty()) {
            System.out.println("No books were found.");
        } else {
            for (Book book : foundBooks) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
            }
        }
    }
}



