package com.example.GeekText.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "shopping_carts")
public class ShoppingCart {
    @Id
    private String id;
    private String userId;

 
    private List<Book> books;

        

    public ShoppingCart(String userId) {
        this.userId = userId;
        this.books = new ArrayList<>();
    }

    public void addBook (Book book) {
        this.books.add(book);
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

    public boolean removeBook(Book book) {
        return this.books.remove(book);
    }

    public double getSubtotal() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }

}
