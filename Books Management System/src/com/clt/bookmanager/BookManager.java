package com.clt.bookmanager;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
import java.io.*;

public class BookManager {
    private List<Book> books = new ArrayList<>();
    private static final String FILE_PATH = "data/books.csv"; // 数据文件路径


    // 添加书籍
    public void addBook(Book book) {
        books.add(book);
    }

    // 获取所有书籍
    public List<Book> getAllBooks() {
        return new ArrayList<>(books); // 返回书籍的新列表以保持封装性
    }

    // 搜索书籍的方法
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

    // 根据ISBN更新书籍
    public void updateBook(String isbn, Book newBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.set(i, newBook);
                return;
            }
        }
    }

    // 根据ISBN删除书籍
    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    // 保存书籍数据到文件
    public void saveBooksToFile() {
        try (PrintWriter writer = new PrintWriter(new File(FILE_PATH))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor() + "," + book.getIsbn());
            }
        } catch (FileNotFoundException e) {
            System.err.println("无法保存书籍数据：" + e.getMessage());
        }
    }

    // 从文件加载书籍数据
    public void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    books.add(new Book(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("无法加载书籍数据：" + e.getMessage());
        }
    }
}

