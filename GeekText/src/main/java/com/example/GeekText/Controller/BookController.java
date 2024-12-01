package com.example.GeekText.Controller;


import com.example.GeekText.Model.Book;
import com.example.GeekText.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) { //in postman request url for adding books http://localhost:8080/api/books/add
        return bookService.addBook(book);
    }

    @GetMapping("/genre/{genre}") //http://localhost:8081/api/books/genre/Technology
    public List<Book> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }

    @GetMapping("/top-sellers") //http://localhost:8081/api/books/top-sellers
    public List<Book> getTopSellers() {
        return bookService.getTopSellers();
    }

    @GetMapping("/rating/{rating}") //http://localhost:8081/api/books/rating/4.7
    public List<Book> getBooksByRating(@PathVariable double rating) {
        return bookService.getBooksByRating(rating);
    }

    @PutMapping("/discount") //http://localhost:8081/api/books/discount?publisher=Prentice Hall&discountPercent=10
    public ResponseEntity<String> discountBooksByPublisher(
            @RequestParam String publisher,
            @RequestParam double discountPercent) {

        bookService.discountBooksByPublisher(publisher, discountPercent);
        return ResponseEntity.ok("Discount applied successfully.");
    }


}
