package com.example.GeekText.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.GeekText.Model.Book;

public interface BookRepo extends MongoRepository<Book, String> {
    Optional<Book> findByBookId(String bookId);
}