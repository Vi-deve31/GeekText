package com.example.GeekText.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeekText.model.Book;
import com.example.GeekText.service.CartService;




@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {


    @Autowired
    private CartService cartService;



//get subtotal
    @GetMapping("/{userId}/subtotal")
    public ResponseEntity <Double> getSubtotal(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getSubtotal(userId));
    }

//adding book in the shopping cart with objectid
    @PostMapping("/{userId}/add/{bookId}")
    public ResponseEntity<Void> addBookToCart(@PathVariable String userId, @PathVariable String bookId) {
       cartService.addBookToCart(userId, bookId); 
       return ResponseEntity.ok().build();
    }
    

//get list of books in shopping cart
    @GetMapping("/{userId}/books")
    public ResponseEntity<List<Book>> getBooksInCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getBooksInShoppingCart(userId));
    }

//remove book from the shopping cart
    @DeleteMapping("/{userId}/remove/{bookId}")
    public ResponseEntity<Void> removeBookFromCart(@PathVariable String userId, @PathVariable String bookId) {
        cartService.removeBookFromShoppingCart(userId, bookId);
        return ResponseEntity.ok().build();
    }
        
    }
