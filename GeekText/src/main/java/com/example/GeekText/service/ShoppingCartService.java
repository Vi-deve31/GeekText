package com.example.GeekText.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.GeekText.model.Book;
import com.example.GeekText.model.ShoppingCart;
import com.example.GeekText.repository.BookRepo;
import com.example.GeekText.repository.ShoppingCartRepo;

@Service
public class ShoppingCartService {
    
    private final ShoppingCartRepo shoppingCartRepo;
    private final BookRepo bookRepo;

    public ShoppingCartService (ShoppingCartRepo shoppingCartRepo, BookRepo bookRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.bookRepo = bookRepo;
    }


    public double getSubtotal(String userId) {
       return shoppingCartRepo.findByUserId(userId)
       .map(ShoppingCart::getSubtotal)
       .orElse(0.0);
    }

    public void addBookToCart(String userId, String bookId) {
        ShoppingCart cart = shoppingCartRepo.findByUserId(userId)
        .orElse(new ShoppingCart(userId));

        Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book does not exist"));
        cart.addBook(book);
        shoppingCartRepo.save(cart);
    }


    public List<Book> getBooksInCart(String userId) {
       return shoppingCartRepo.findByUserId(userId)
        .map(ShoppingCart::getBooks)
        .orElse(Collections.emptyList());
    }
    
    public void removeBookFromCart(String userId, String bookId) {
         shoppingCartRepo.findByUserId(userId).ifPresent(cart -> {
            cart.getBooks().removeIf(book -> book.getId().equals(bookId));
            shoppingCartRepo.save(cart);
        });
        
        }
    }
