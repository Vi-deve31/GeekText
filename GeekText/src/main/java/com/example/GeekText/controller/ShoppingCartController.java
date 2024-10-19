package com.example.GeekText.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeekText.model.Book;
import com.example.GeekText.model.ShoppingCart;
import com.example.GeekText.repository.BookRepo;
import com.example.GeekText.repository.ShoppingCartRepo;




@RestController
@RequestMapping("api/shoppingcart")
public class ShoppingCartController {

@Autowired
private ShoppingCartRepo shoppingCartRepo;

@Autowired
private BookRepo bookRepo;



//get subtotal
@GetMapping("/{userId}/subtotal")
public ResponseEntity<Double> getSubtotal(@PathVariable String userId) {
    Optional<ShoppingCart> cartOpt = shoppingCartRepo.findByUserId(userId);
    if (cartOpt.isPresent()) {
        return ResponseEntity.ok(cartOpt.get().getSubtotal());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(0.0);
    }
}

//adding book in the shopping cart
@PostMapping("/{userId}/add/{bookId}")
public ResponseEntity<ShoppingCart> addBooktoCart(@PathVariable String userId, @PathVariable String bookId) {
    Optional<ShoppingCart> cartOpt = shoppingCartRepo.findByUserId(userId);
    Optional<Book> bookOpt = bookRepo.findById(bookId);

    if (!bookOpt.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //404
    }

    //if shopping cart exists
    ShoppingCart cart = shoppingCartRepo.findByUserId(userId).orElseGet(() -> new ShoppingCart(userId));
    cart.addBook(bookOpt.get());
    shoppingCartRepo.save(cart);

    return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

//get list of books in shopping cart
@GetMapping("/{userId}/books")
public ResponseEntity<?> getBooksInShoppingCart(@PathVariable String userId) {
    Optional <ShoppingCart> cartOpt = shoppingCartRepo.findByUserId(userId);
    if (cartOpt.isPresent()) {
        return ResponseEntity.ok(cartOpt.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Cart doesn't exist for user: " + userId); 
    }
}


//remove book from the shopping cart
@DeleteMapping("/{userId}/remove/{bookId}")
public ResponseEntity<?> removeBookFromShoppingCart(@PathVariable String userId, @PathVariable String bookId) {
    Optional<ShoppingCart> cartOpt = shoppingCartRepo.findByUserId(userId);
    Optional<Book> bookOpt = bookRepo.findById(bookId);

    if (cartOpt.isPresent() && bookOpt.isPresent()) {
        ShoppingCart cart = cartOpt.get();
        cart.removeBook(bookOpt.get());
        shoppingCartRepo.save(cart);
        return ResponseEntity.ok(cart);
    } else {
       return ResponseEntity.status(HttpStatus.NOT_FOUND)
       .body("Either Book or Shopping Cart not found.");
    }
}
}

