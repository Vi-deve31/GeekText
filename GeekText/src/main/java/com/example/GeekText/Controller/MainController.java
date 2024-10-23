package com.example.GeekText.Controller;

import com.example.GeekText.Model.BookDetails;
import com.example.GeekText.Repository.BookDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
