public class Store {
    private Book[] Books;
    private int total=0;
/////////////////////////////////////////////////////////////////
    public Store(int number) {
        Books=new Book[number];
    }
    private boolean isFull() {
        return total == Books.length;
    }
////////////////////////////////////////////////////////////////
    private boolean isEmpty() {
        return total == 0;
    }
    public boolean add(Book aBooks) {
        if (isFull()) {
            return false;
        } else {
            Books[total] = aBooks;
            total++;
            return true;
        }
    }
///////////////////////////////////////////////////////////////
    public String list() {
        if (isEmpty()) {
            return "No yokes in the store";
        } else {
            String listOfBooks = "";
            for (int i = 0; i < total; i++) {
                listOfBooks += i + ": " + Books[i] + "\n";
            }
            return listOfBooks;
        }
}}

