package com.example.GeekText.Controller;

import com.example.GeekText.Model.BookDetails;
import com.example.GeekText.Repository.BookDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {

    @Autowired
    BookDetailsRepo bookDetailsRepo;

    @PostMapping("/addBook")
    public void addBook(@RequestBody BookDetails bookDetails) {
        bookDetailsRepo.save(bookDetails);
    }
}
