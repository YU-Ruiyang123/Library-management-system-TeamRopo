import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookManagerGUI {
    private BookManager manager; // BookManager实例
    private JFrame frame;
    private JTextArea textArea;
    private JTextField titleField, authorField, isbnField, searchField;

    public BookManagerGUI(BookManager manager) {
        this.manager = manager;
        createAndShowGUI();
        displayAllBooks(); // 启动时显示所有书籍
    }

    private void createAndShowGUI() {
        frame = new JFrame("Library management system");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);
        frame.setLocationRelativeTo(null); // 设置窗口居中


        // 创建文本域
        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // 创建控制面板
        JPanel controlPanel = new JPanel();
        titleField = new JTextField(10);
        authorField = new JTextField(10);
        isbnField = new JTextField(10);
        searchField = new JTextField(10);

        JButton addButton = new JButton("Add a book");
        addButton.addActionListener(new AddBookListener());

        JButton searchButton = new JButton("Search for a book");
        searchButton.addActionListener(new SearchBookListener());

        JButton updateButton = new JButton("Update book");
        updateButton.addActionListener(new UpdateBookListener());

        JButton deleteButton = new JButton("Delete book");
        deleteButton.addActionListener(new DeleteBookListener());

// 将按钮添加到控制面板
        controlPanel.add(updateButton);
        controlPanel.add(deleteButton);


        controlPanel.add(new JLabel("Title:"));
        controlPanel.add(titleField);
        controlPanel.add(new JLabel("Author:"));
        controlPanel.add(authorField);
        controlPanel.add(new JLabel("ISBN:"));
        controlPanel.add(isbnField);
        controlPanel.add(addButton);
        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // 显示所有书籍的方法
    private void displayAllBooks() {
        textArea.setText(""); // 清空文本域
        List<Book> books = manager.getAllBooks();
        for (Book book : books) {
            textArea.append(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")\n");
        }
    }

    // 添加书籍的监听器
    class AddBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();

            if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
                manager.addBook(new Book(title, author, isbn));
                manager.saveBooksToFile();
                displayAllBooks(); // 更新显示
                titleField.setText("");
                authorField.setText("");
                isbnField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required！", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // 搜索书籍的监听器
    class SearchBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = searchField.getText();
            List<Book> foundBooks = manager.searchBooks(keyword);
            textArea.setText(""); // 清空文本域
            for (Book book : foundBooks) {
                textArea.append(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")\n");
            }
        }
    }

    // 更新书籍的监听器
    class UpdateBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String isbn = isbnField.getText();
            String title = titleField.getText();
            String author = authorField.getText();

            if (!isbn.isEmpty()) {
                manager.updateBook(isbn, new Book(title, author, isbn));
                manager.saveBooksToFile();
                displayAllBooks(); // 更新显示
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter the ISBN of the book you want to update！", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // 删除书籍的监听器
    class DeleteBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String isbn = isbnField.getText();

            if (!isbn.isEmpty()) {
                manager.deleteBook(isbn);
                manager.saveBooksToFile();
                displayAllBooks(); // 更新显示
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter the ISBN of the book you want to delete！", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.loadBooksFromFile();
        new BookManagerGUI(manager);
    }
}
