package persistence;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private int id;
    private int Book;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private List<Book> bookList = new ArrayList<>();

    public int getBook() {
        return Book;
    }

    public void setBook(int book) {
        Book = book;
    }

    public List<persistence.Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<persistence.Book> bookList) {
        this.bookList = bookList;
    }

    public List <Book> addBook(Book book){
        bookList.add(book);
        return bookList;
    }

    public List <Book> removeAbook(Book book){
        bookList.remove(book);
        return bookList;
    }


}


