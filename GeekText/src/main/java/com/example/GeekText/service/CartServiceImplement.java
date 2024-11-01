package com.example.GeekText.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GeekText.model.Book;
import com.example.GeekText.model.ShoppingCart;
import com.example.GeekText.repository.BookRepo;
import com.example.GeekText.repository.ShoppingCartRepo;


@Service
public class CartServiceImplement implements CartService {

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private BookRepo bookRepo;

    @Override
    public double getSubtotal(String userId) {
        return shoppingCartRepo.findByUserId(userId)
        .map(ShoppingCart::getSubtotal)
        .orElse(0.0);
    }

    @Override
    public void addBookToCart(String userId, String bookId) {
        Book book = bookRepo.findById(bookId)
        .orElseThrow(() -> new RuntimeException("Book does not exist"));

        ShoppingCart cart = shoppingCartRepo.findByUserId(userId) 
           .orElseGet(() -> new ShoppingCart(userId));

        cart.addBook(book);
        shoppingCartRepo.save(cart);
    }

    @Override
    public List<Book> getBooksInShoppingCart (String userId) {
        return shoppingCartRepo.findByUserId(userId)
        .map(ShoppingCart::getBooks)
        .orElse(new ArrayList<>());
    }

    @Override
    public void removeBookFromShoppingCart (String userId, String bookId) {
        ShoppingCart cart = shoppingCartRepo.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart cannot be found."));

        Book book = bookRepo.findById(bookId)
        .orElseThrow(() -> new RuntimeException("Book cannot be found"));

        cart.removeBook(book);
        shoppingCartRepo.save(cart);
    }
}

