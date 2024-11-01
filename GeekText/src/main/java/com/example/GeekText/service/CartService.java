package com.example.GeekText.service;

import java.util.List;

import com.example.GeekText.model.Book;

public interface CartService {
    double getSubtotal(String userId);
    void addBookToCart(String userId, String bookId);
    List<Book> getBooksInShoppingCart(String userId);
    void removeBookFromShoppingCart(String userId, String bookId);
    
}
