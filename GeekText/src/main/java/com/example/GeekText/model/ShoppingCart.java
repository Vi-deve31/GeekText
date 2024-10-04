package com.example.GeekText.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "shopping_carts")
public class ShoppingCart {
    @Id
    private String id;
    private String userId;
    private List<Book> books;

    //constructors
    public ShoppingCart() {
        this.books = new ArrayList<>();
    }

    public ShoppingCart(String userId) {
        this.userId = userId;
        this.books = new ArrayList<>();
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id; 
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId (String userId) {
        this.userId = userId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book>books) {
        this.books = books; 
    }

    //helper methods
    public void addBook(Book book) {
        this.books.add(book);
    }
    
    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public double getSubtotal() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }

}
