package com.example.GeekText.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.GeekText.model.Book;



public interface BookRepo extends MongoRepository<Book, String> {
      
}
