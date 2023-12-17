package com.clt.bookmanager;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
import java.io.*;

public class BookManager {
    private List<Book> books = new ArrayList<>();
    private static final String FILE_PATH = "data/books.csv"; //Data file path / 数据文件路径


    //Add books / 添加书籍
    public void addBook(Book book) {
        books.add(book);
    }

    // Get all Books /获取所有书籍
    public List<Book> getAllBooks() {
        return new ArrayList<>(books); // Returns a new list of books to preserve encapsulation /返回书籍的新列表以保持封装性
    }

    // Ways to search for books /搜索书籍的方法
    public List<Book> searchBooks(String keyword) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getIsbn().toLowerCase().contains(keyword.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    //Update books according to ISBN /根据ISBN更新书籍
    public void updateBook(String isbn, Book newBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.set(i, newBook);
                return;
            }
        }
    }

    //Delete books according to ISBN / 根据ISBN删除书籍
    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    // Save book data to a file /保存书籍数据到文件
    public void saveBooksToFile() {
        try (PrintWriter writer = new PrintWriter(new File(FILE_PATH))) {
            for (Book book : books) {
                writer.println(formatCsvField(book.getTitle()) + "," +
                        formatCsvField(book.getAuthor()) + "," +
                        formatCsvField(book.getIsbn()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to save book data：" + e.getMessage());
        }
    }

    public void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (data.length >= 3) {
                    books.add(new Book(unformatCsvField(data[0]),
                            unformatCsvField(data[1]),
                            unformatCsvField(data[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to load book data：" + e.getMessage());
        }
    }

    private String formatCsvField(String data) {
        return "\"" + data.replace("\"", "\"\"") + "\"";
    }

    private String unformatCsvField(String data) {
        if (data.startsWith("\"") && data.endsWith("\"")) {
            data = data.substring(1, data.length() - 1); // Remove the double quotes /移除首尾双引号
        }
        return data.replace("\"\"", "\""); // Converts two double quotes to one double quotation mark /将两个双引号转换为一个双引号
    }
}
