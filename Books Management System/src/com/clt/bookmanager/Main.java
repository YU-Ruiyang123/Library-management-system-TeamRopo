// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
/*public class Main {
    public static void main(String[] args) {
        // 当文本光标位于高亮显示的文本处时按 Alt+Enter，
        // 可查看 IntelliJ IDEA 对于如何修正该问题的建议。
        System.out.printf("Hello and welcome!");

        // 按 Shift+F10 或点击装订区域中的绿色箭头按钮以运行脚本。
        for (int i = 1; i <= 5; i++) {

            // 按 Shift+F9 开始调试代码。我们已为您设置了一个断点，
            // 但您始终可以通过按 Ctrl+F8 添加更多断点。
            System.out.println("i = " + i);
        }
    }
}

 */

package com.clt.bookmanager;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static BookManager manager = new BookManager(); // 用于管理书籍的对象
    private static Scanner scanner = new Scanner(System.in); // 用于获取用户输入的扫描器

    public static void main(String[] args) {
        manager.loadBooksFromFile(); // 程序启动时加载书籍数据
        while (true) {
            //System.out.println("Current working directory: " + System.getProperty("user.dir")); // 获取程序运行时的工作目录
            System.out.println("\n图书管理系统:");
            System.out.println("1. 添加书籍");
            System.out.println("2. 显示所有书籍");
            System.out.println("3. 更新书籍");
            System.out.println("4. 删除书籍");
            System.out.println("5. 搜索书籍");
            System.out.println("6. 退出");
            System.out.print("请输入您的选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除输入行的换行符

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    searchBooks(); // 调用搜索书籍的方法
                    break;
                case 6:
                    System.out.println("退出系统。");
                    // 注意：这里我们不再关闭scanner，避免关闭System.in
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }

    private static void addBook() {
        System.out.print("输入书名: ");
        String title = scanner.nextLine();

        System.out.print("输入作者: ");
        String author = scanner.nextLine();

        System.out.print("输入ISBN: ");
        String isbn = scanner.nextLine();

        while (!isValidISBN(isbn)) {
            System.out.print("无效的ISBN，请按正确格式重新输入ISBN（例如: 123456789X 或 123-456-789-X123）: ");
            isbn = scanner.nextLine();
        }

        manager.addBook(new Book(title, author, isbn));
        System.out.println("书籍已添加。");
    }

    private static void displayAllBooks() {
        List<Book> books = manager.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("没有找到书籍。");
            return;
        }

        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
        }
    }

    private static boolean isValidISBN(String isbn) {
        String regex = "^(97(8|9))?\\d{9}(\\d|X)$"; // ISBN-10或ISBN-13的正则表达式
        // 移除ISBN中的短划线
        String isbnClean = isbn.replace("-", "");
        return Pattern.matches(regex, isbnClean);
    }


    private static void updateBook() {
        System.out.print("输入要更新的书籍的ISBN: ");
        String isbn = scanner.nextLine();

        // 假设我们只更新书名和作者，不更新ISBN
        System.out.print("输入新书名: ");
        String newTitle = scanner.nextLine();

        System.out.print("输入新作者: ");
        String newAuthor = scanner.nextLine();

        // 使用ISBN查找并更新书籍
        manager.updateBook(isbn, new Book(newTitle, newAuthor, isbn));
        System.out.println("书籍已更新。");
    }

    private static void deleteBook() {
        System.out.print("输入要删除的书籍的ISBN: ");
        String isbn = scanner.nextLine();

        manager.deleteBook(isbn);
        System.out.println("书籍已删除。");
    }

    private static void searchBooks() {
        System.out.print("输入搜索关键字: ");
        String keyword = scanner.nextLine();
        List<Book> foundBooks = manager.searchBooks(keyword);
        if (foundBooks.isEmpty()) {
            System.out.println("没有找到相关书籍。");
        } else {
            for (Book book : foundBooks) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
            }
        }
    }
}


// 搜索书籍的新方法
