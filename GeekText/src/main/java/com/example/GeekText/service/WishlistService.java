package com.example.GeekText.Service;

import com.example.GeekText.Model.Book;
import com.example.GeekText.Model.Wishlist;
import com.example.GeekText.Repository.BookRepository;
import com.example.GeekText.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private BookRepository bookRepository;

    public Wishlist createWishlist(String userId, String name) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setName(name);
        return wishlistRepository.save(wishlist);
    }

    public Optional<Wishlist> addBookToWishlist(String wishlistId, String bookId) {
        Optional<Wishlist> wishlistOpt = wishlistRepository.findById(wishlistId);
        if (wishlistOpt.isPresent()) {
            Wishlist wishlist = wishlistOpt.get();
            if (!wishlist.getBookIds().contains(bookId)) {
                wishlist.getBookIds().add(bookId);
                wishlistRepository.save(wishlist);
            }
        }
        return wishlistOpt;
    }

    public Optional<Wishlist> removeBookFromWishlist(String wishlistId, String bookId) {
        Optional<Wishlist> wishlistOpt = wishlistRepository.findById(wishlistId);
        if (wishlistOpt.isPresent()) {
            Wishlist wishlist = wishlistOpt.get();
            if (wishlist.getBookIds().remove(bookId)) {
                wishlistRepository.save(wishlist);
            }
        }
        return wishlistOpt;
    }

    public Optional<List<Book>> getWishlistBooks(String wishlistId) {
        Optional<Wishlist> wishlistOpt = wishlistRepository.findById(wishlistId);
        if (wishlistOpt.isPresent()) {
            List<Book> books = wishlistOpt.get().getBookIds().stream()
                    .map(bookId -> bookRepository.findById(bookId))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            return Optional.of(books);
        }
        return Optional.empty();
    }
}
