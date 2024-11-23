package com.example.GeekText.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.GeekText.model.Book;

public interface BookRepo extends MongoRepository<Book, String> {
    Optional<Book> findByBookId(String bookId);
}