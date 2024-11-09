package com.geektext.controllers;

import org.springframework.web.bind.annotation.*;
import com.geektext.services.WishlistService;
import com.geektext.models.Book;
import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // Create a wishlist
    @PostMapping("/create")
    public String createWishlist(@RequestParam String userId, @RequestParam String name) {
        wishlistService.createWishlist(userId, name);
        return "Wishlist created successfully.";
    }

    // Add a book to a wishlist
    @PostMapping("/addBook")
    public String addBookToWishlist(@RequestParam String wishlistId, @RequestParam String bookId) {
        wishlistService.addBookToWishlist(wishlistId, bookId);
        return "Book added to wishlist.";
    }

    // Remove a book from a wishlist
    @DeleteMapping("/removeBook")
    public String removeBookFromWishlist(@RequestParam String wishlistId, @RequestParam String bookId) {
        wishlistService.removeBookFromWishlist(wishlistId, bookId);
        return "Book removed from wishlist.";
    }

    // Get books in a wishlist
    @GetMapping("/books")
    public List<Book> getBooksInWishlist(@RequestParam String wishlistId) {
        return wishlistService.getBooksInWishlist(wishlistId);
    }
}
