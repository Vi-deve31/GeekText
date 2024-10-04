package com.example.GeekText.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeekText.model.Book;
import com.example.GeekText.service.ShoppingCartService;




@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {
    
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{userId}/subtotal")
    public ResponseEntity<Double> getSubtotal(@PathVariable String userId) {
        double subtotal = shoppingCartService.getSubtotal(userId);
        return ResponseEntity.ok(subtotal);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Void> addBookToCart (@PathVariable String userId, @RequestParam String bookId) {
        shoppingCartService.addBookToCart(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<List<Book>> getBooksInCart(@PathVariable String userId) {
        List<Book> books = shoppingCartService.getBooksInCart(userId);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<Void> removeBookFromCart (@PathVariable String userId, @RequestParam String bookId) {
        shoppingCartService.removeBookFromCart(userId, bookId);
        return ResponseEntity.ok().build();
    }
}

    

