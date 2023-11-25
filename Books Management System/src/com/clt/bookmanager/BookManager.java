package com.clt.bookmanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookManager {
    private List<Book> books = new ArrayList<>();

    // 添加书籍
    public void addBook(Book book) {
        books.add(book);
    }

    // 获取所有书籍
    public List<Book> getAllBooks() {
        return new ArrayList<>(books); // 返回书籍的新列表以保持封装性
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
}
