package com.example.GeekText.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
    
    
   @Id
   private String bookId;

   private String title;
   private String author;
   private double price;
   private String isbn;



    public Book() {}
    
    public Book(String bookId, String title, String author, double price, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    
}

    //getters and setters   
    public String getBookId() {
        return bookId;
    }

    public void setBookId (String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn (String isbn) {
        this.isbn = isbn; 
    }

    @Override
    public String toString() {
        return "Book{" + 
        "id='" + bookId + '\'' + 
        ", title='" + title + '\'' + 
        ", author='" + author  + '\'' +
        ", price=" + price + 
        ", isbn='" + isbn + '\'' + 
        '}';

    }
}