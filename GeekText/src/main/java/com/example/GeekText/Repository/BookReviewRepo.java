package com.example.GeekText.Repository;

import com.example.GeekText.Model.BookReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;



/* This interface (not a class) links our student class with our MongoDB compass.
 with the help of the repo we will be able to insert/find values from our mongodb compass.
 Interfaces allow for flexibility.
 */

public interface BookReviewRepo extends MongoRepository<BookReview, Integer> { // <> takes in Student (name of class), followed by primary key ID

    @Transactional
    @Query("{ 'userId': ?0, 'isbn': ?1 }")
    void updateRating(String userId, String isbn, Integer rating, LocalDate ratingDate);

    @Transactional
    @Query("{ 'userId': ?0, 'isbn': ?1 }")
    void updateComments(String userId, String isbn, String comments, LocalDate commentDate);

    // Method to find by userId and isbn for validation in the MainController
    BookReview findByUserIdAndIsbn(Integer userId, String isbn);

    List<BookReview> findByIsbn(String isbn);
}
