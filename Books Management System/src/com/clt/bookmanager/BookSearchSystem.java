package com.clt.bookmanager;
import java.util.List;
import java.util.Scanner;

public class BookSearchSystem {
    public static void displayResults(String category, List<Book> results) {
        System.out.println(category + "匹配的书籍：");
        if (results.isEmpty()) {
            System.out.println("未找到匹配的书籍。");
        } else {
            for (Book book : results) {
                System.out.println(" - " + book.getTitle() + " by " + book.getAuthor());
            }
        }
        System.out.println();
    }

    public static void displayIsbnResult(Book result) {
        System.out.println("ISBN匹配的书籍：");
        if (result == null) {
            System.out.println("未找到匹配的书籍。");
        } else {
            System.out.println(" - " + result.getTitle() + " by " + result.getAuthor());
        }
    }
}
