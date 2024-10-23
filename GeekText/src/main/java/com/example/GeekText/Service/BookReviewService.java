package com.example.GeekText.Service;

import com.example.GeekText.Model.BookReview;
import com.example.GeekText.Repository.BookReviewRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookReviewService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateRating(Integer userId, String isbn, Integer rating, LocalDate ratingDate) {
        // Create the query to find the document with the specified userId and isbn
        Query query = new Query(Criteria.where("userId").is(userId).and("isbn").is(isbn));

        // Create an update object that sets the rating and ratingDate fields
        Update update = new Update().set("rating", rating).set("ratingDate", ratingDate);

        // Perform the update on the matched document
        mongoTemplate.updateFirst(query, update, BookReview.class);
    }

    public void updateComment(Integer userId, String isbn, String comment, LocalDate commentDate) {
        // Create the query to find the document with the specified userId and isbn
        Query query = new Query(Criteria.where("userId").is(userId).and("isbn").is(isbn));

        // Create an update object that sets the comment and commentDate fields
        Update update = new Update().set("comment", comment).set("commentDate", commentDate);

        // Perform the update on the matched document
        mongoTemplate.updateFirst(query, update, BookReview.class);
    }

    public List<String> getCommentsForBook(String isbn) {
        // Create a query to find all reviews for the given ISBN
        Query query = new Query(Criteria.where("isbn").is(isbn));

        // Execute the query and retrieve all BookReview documents that match
        List<BookReview> reviews = mongoTemplate.find(query, BookReview.class);

        // Extract comments from the reviews
        List<String> comments = new ArrayList<>();
        for (BookReview review : reviews) {
            comments.add(review.getComment()); // Add each comment to the list
        }

        return comments; // Return the list of comments
    }

    @Autowired
    private BookReviewRepo bookReviewRepo;
    public Float calculateAverageRating(String isbn) {
        // Fetch all reviews for the given ISBN
        List<BookReview> reviews = bookReviewRepo.findByIsbn(isbn);

        // If no reviews found, return null
        if (reviews.isEmpty()) {
            return null;
        }

        // Calculate the average rating
        float sum = 0;
        for (BookReview review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size(); // Return the average
    }

}
