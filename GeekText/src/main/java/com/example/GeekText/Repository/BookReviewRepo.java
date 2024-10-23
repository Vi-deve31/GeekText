package com.example.GeekText.Repository;

import com.example.GeekText.Model.BookReview;
import org.springframework.data.mongodb.repository.MongoRepository;

/* This interface (not a class) links our student class with our MongoDB compass.
 with the help of the repo we will be able to insert/find values from our mongodb compass.
 Interfaces allow for flexibility.
 */
public interface BookReviewRepo extends MongoRepository<BookReview, Integer> { // <> takes in Student (name of class), followed by primary key ID
}
