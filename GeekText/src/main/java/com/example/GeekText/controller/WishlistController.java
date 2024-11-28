package com.example.GeekText.Controller;

import com.example.GeekText.Model.Book;
import com.example.GeekText.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<String> createWishlist(@RequestParam String userId, @RequestParam String name) {
        wishlistService.createWishlist(userId, name);
        return ResponseEntity.ok("Wishlist created successfully");
    }

    @PostMapping("/{wishlistId}/books")
    public ResponseEntity<String> addBookToWishlist(@PathVariable String wishlistId, @RequestParam String bookId) {
        Optional<?> result = wishlistService.addBookToWishlist(wishlistId, bookId);
        if (result.isPresent()) {
            return ResponseEntity.ok("Book added to wishlist");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{wishlistId}/books")
    public ResponseEntity<String> removeBookFromWishlist(@PathVariable String wishlistId, @RequestParam String bookId) {
        Optional<?> result = wishlistService.removeBookFromWishlist(wishlistId, bookId);
        if (result.isPresent()) {
            return ResponseEntity.ok("Book removed from wishlist");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{wishlistId}/books")
    public ResponseEntity<?> listWishlistBooks(@PathVariable String wishlistId) {
        Optional<List<Book>> booksOpt = wishlistService.getWishlistBooks(wishlistId);
        if (booksOpt.isPresent()) {
            return ResponseEntity.ok(booksOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
}
