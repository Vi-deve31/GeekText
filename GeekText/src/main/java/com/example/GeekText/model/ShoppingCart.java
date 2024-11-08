package com.example.GeekText.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "cart") 
public class ShoppingCart {
    @Id
    private String shoppingCartId;
    private String userId;
    private List<BooksInCart> books = new ArrayList<>();
    private String createdAt;
    private String updatedAt;

    //class for the books in the cart
    public static class BooksInCart {
        private String bookId;
        private String title;
        private String author;
        private double price;

        public String getBookId(){
            return bookId;
        }
        public void setBookId(String bookId) {
            this.bookId = bookId;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getAuthor(){
            return author;
        }
        public void setAuthor (String author) {
            this.author = author;
        }
        public double getPrice(){
            return price;
        }
        public void setPrice (double price) {
            this.price = price;
        }
    }


    public String getShoppingCartId () {
        return shoppingCartId;
    }
    public void setShoppingCartId (String shoppingCartId) {
        this.shoppingCartId = shoppingCartId; 
    } 

    public String getUserId () {
        return userId;
    }

    public void setUserId (String userId) {
        this.userId = userId; 
    } 

    public List<BooksInCart> getBooks() {
        return books;
    }
    public void setBooks (List<BooksInCart> books) {
        this.books = books;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}