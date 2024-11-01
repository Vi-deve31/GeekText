package com.example.GeekText.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.GeekText.model.Book;



public interface BookRepo extends MongoRepository<Book, String> {
      List<Book> findByAuthor(String author);
      List<Book> findByTitleIgnoreCase(String title);
      List<Book> findByPriceBetween(double minPrice, double maxPrice);
      List<Book> findByTitleContainingIgnoreCase(String keyword);
      List<Book> findByAuthorIgnoreCase(String keyword);


      @Query("{ 'author': ?0,  'price': { $lt: ?1} }")
      List<Book> findByAuthorAndPriceLessThan(String author, double price);
      Optional<Book> findFirstByTitleIgnoreCase(String title);
}
