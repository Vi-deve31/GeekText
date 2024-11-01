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



    public Book() {}
    
    public Book(String bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    
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

    @Override
    public String toString() {
        return "Book{" + 
        "id='" + bookId + '\'' + 
        ", title='" + title + '\'' + 
        ", author='" + author  + '\'' +
        ", price=" + price +
        '}';

    }
}