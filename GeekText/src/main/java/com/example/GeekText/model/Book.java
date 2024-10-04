package com.example.GeekText.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
    @Id
    private String id; 
    private String title;
    private String author;
    private double price; //for the calculated subtotal
    

    //constructors
    public Book() {}
    
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    
}

    //getters and setters   
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
}