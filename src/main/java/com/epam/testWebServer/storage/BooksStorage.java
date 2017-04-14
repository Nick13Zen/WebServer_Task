package main.java.com.epam.testWebServer.storage;


import main.java.com.epam.testWebServer.bean.Book;
import main.java.com.epam.testWebServer.storage.exception.StorageException;

import java.util.ArrayList;
import java.util.List;

public class BooksStorage {

    private static final String BOOK_NOT_FOUND_EXCEPTION_TEXT = "Book not found";

    private List<Book> books = new ArrayList<>();

    {
        books.add(new Book(1, "first book", "q"));
        books.add(new Book(2, "second book", "w"));
        books.add(new Book(3, "third book", "e"));
    }

    private static BooksStorage instance;

    private BooksStorage() {

    }

    public static BooksStorage getInstance() {
        if (instance == null) {
            instance = new BooksStorage();
        }
        return instance;
    }

    public void setBook(Book book) {
        books.add(book);
    }

    public Book getBook(int id) throws StorageException {
        Book resultBook = null;
        for (Book book : books) {
            if (book.getId() == id) {
                resultBook = book;
            }
        }
        if (resultBook == null) {
            throw new StorageException(BOOK_NOT_FOUND_EXCEPTION_TEXT);
        }

        return resultBook;
    }

    public void deleteBook(int id) {
        books.remove(id);
    }

    public List<Book> getBooks() {
        return books;
    }
}
