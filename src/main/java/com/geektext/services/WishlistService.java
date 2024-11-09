package com.geektext.services;

import org.springframework.stereotype.Service;
import com.geektext.repositories.WishlistRepository;
import com.geektext.repositories.BookRepository;
import com.geektext.repositories.UserRepository;
import com.geektext.models.Wishlist;
import com.geektext.models.Book;
import com.geektext.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public WishlistService(WishlistRepository wishlistRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Wishlist createWishlist(String userId, String name) {
        Wishlist wishlist = new Wishlist();
        wishlist.setName(name);
        wishlist.setUserId(userId);
        wishlist.setBookIds(new ArrayList<>());
        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        // Add wishlist ID to user's wishlistIds
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getWishlistIds() == null) {
                user.setWishlistIds(new ArrayList<>());
            }
            user.getWishlistIds().add(savedWishlist.getId());
            userRepository.save(user);
        }

        return savedWishlist;
    }

    public void addBookToWishlist(String wishlistId, String bookId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        if (!wishlist.getBookIds().contains(bookId)) {
            wishlist.getBookIds().add(bookId);
            wishlistRepository.save(wishlist);
        }
    }

    public void removeBookFromWishlist(String wishlistId, String bookId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        if (wishlist.getBookIds().contains(bookId)) {
            wishlist.getBookIds().remove(bookId);
            wishlistRepository.save(wishlist);
        }
    }

    public List<Book> getBooksInWishlist(String wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        return bookRepository.findAllById(wishlist.getBookIds());
    }
}
