package com.example.GeekText.Controller;

import com.example.GeekText.Model.AuthorDetails;
import com.example.GeekText.Model.BookDetails;
import com.example.GeekText.Repository.AuthorDetailsRepo;
import com.example.GeekText.Repository.BookDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MainController {

    @Autowired
    BookDetailsRepo bookDetailsRepo;

    @PostMapping("/addBook")
    public void addBook(@RequestBody BookDetails bookDetails) {
        bookDetailsRepo.save(bookDetails);
    }


    @GetMapping("/getBook/{isbn}")
    public BookDetails getBook(@PathVariable Long isbn) {
        return bookDetailsRepo.findByIsbn(isbn).orElse(null);

    }

    @Autowired
    AuthorDetailsRepo authorDetailsRepo;



    @PostMapping("/addAuthor")
    public void addBook(@RequestBody AuthorDetails authorDetails) {
        authorDetailsRepo.save(authorDetails);
    }

    @GetMapping("/getBooksByAuthor/{authorId}")
    public List<BookDetails> getBooksByAuthor(@PathVariable Integer authorId) {
        AuthorDetails author = authorDetailsRepo.findById(authorId).orElse(null);
        if (author != null) {
            return bookDetailsRepo.findByAuthor(author.getFirstName() + " " + author.getLastName());
        }
        return List.of();
    }





}
