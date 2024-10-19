package com.example.GeekText.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeekText.model.Book;
import com.example.GeekText.repository.BookRepo;

@RestController
@RequestMapping("/api/books")
public class BookController {
    

    @Autowired
    private BookRepo bookRepo;

    //adds new book
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {

        return bookRepo.save(book); //saves to db
    }
    
}
