package main.java.com.epam.testWebServer.bean;

public class Book {
    private int id;
    private String author;
    private String title;

    public Book(int id, String author, String title){
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getAuter() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
