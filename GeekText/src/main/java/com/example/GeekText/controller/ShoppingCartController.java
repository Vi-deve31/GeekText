package com.example.GeekText.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeekText.model.ShoppingCart;
import com.example.GeekText.Service.CartService;


@RestController
@RequestMapping("api/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private CartService cartService;

    //retrieve books http://localhost:8080/api/shoppingcart/user001
    @GetMapping("/{userId}")
    public ResponseEntity<List<ShoppingCart.BooksInCart>> getBooks(@PathVariable String userId) {//@PathVariable String bookId) 
        return ResponseEntity.ok(cartService.getBooks(userId));
    }

    // retrieve subtotal  http://localhost:8080/api/shoppingcart/subtotal/user001
    @GetMapping("subtotal/{userId}")
    public ResponseEntity<Double> getSubtotal(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getSubtotal(userId));
    }

    //add a book to a shopping cart http://localhost:8080/api/shoppingcart/user001/book/Binti-1
    @PostMapping("/{userId}/book/{bookId}")
    public ResponseEntity<Void> addBook (@PathVariable String userId, @PathVariable String bookId) {
        cartService.addBook(userId, bookId);
        return ResponseEntity.ok().build();
    }

    //delete a book from the shopping cart http://localhost:8080/api/shoppingcart/user001/book/Binti-1
    @DeleteMapping("/{userId}/book/{bookId}")
    public ResponseEntity<Void> removeBook(@PathVariable String userId, String bookId) {
        cartService.removeBook(userId, bookId);
        return ResponseEntity.ok().build();
    }
}