package com.example.GeekText.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.GeekText.model.Book;

@Repository
public interface BookRepo extends MongoRepository<Book, String> {
    
    //find book by title
    Book findByTitle(String title);

    //find books by author
    List<Book> findByAuthor(String author);

    //find books with price >= given price
    List<Book> findByPricesLessOrEqual(double price);

    //find book titles with specified string (case-insensitive)
    List<Book> findByTitleId(String titlePart);

    @Query("{'price': {$gt: ?0, $lt:  ?1}}")
    //custom query to find books within price range
    List<Book> findByPrice(double minPrice, double maxPrice);
    
}
