package com.example.GeekText.Service;

import com.example.GeekText.Model.Book;
import com.example.GeekText.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }
    public List<Book> getBooksByGenre(String genre) {
        return bookRepo.findByGenre(genre);
    }

    public List<Book> getTopSellers() {
        return bookRepo.findTop10ByOrderByCopiesSoldDesc();
    }

    public List<Book> getBooksByRating(double rating) {
        return bookRepo.findByRatingGreaterThanEqualOrderByRatingAsc(rating);
    }
}
