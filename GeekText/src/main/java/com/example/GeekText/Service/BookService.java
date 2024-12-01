package com.example.GeekText.Service;

import com.example.GeekText.Model.Book;
import com.example.GeekText.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void discountBooksByPublisher(String publisher, double discountPercent) {
        List<Book> books = bookRepo.findAllByPublisher(publisher);

        for (Book book : books) {
            double newPrice = book.getPrice() * (1 - discountPercent / 100);
            //ROUND to 2 decimal places
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            book.setPrice(newPrice);
        }
        bookRepo.saveAll(books);
    }

}
