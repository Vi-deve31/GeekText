package com.example.GeekText.Controller;

import com.example.GeekText.Model.BookReview;
import com.example.GeekText.Repository.BookReviewRepo;
import com.example.GeekText.Service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
public class BookReviewController {

    @Autowired
    private BookReviewRepo bookReviewRepo;  // Inject the repository to fetch data from MongoDB
    @Autowired
    private BookReviewService bookReviewService;  // Inject the service class to handle update logic

    @PostMapping("/postRating")
    public ResponseEntity<String> postRating(@RequestBody BookReview bookReview) {

        // Fetch the BookReview document from MongoDB by userId and isbn
        BookReview existingReview = bookReviewRepo.findByUserIdAndIsbn(bookReview.getUserId(), bookReview.getIsbn());

        // Check if the document exists
        if (existingReview == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book or user not found.");
        }

        // Check if the purchaseStatus is true in the document
        if (!existingReview.isPurchaseStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must purchase the book before rating.");
        }

        // Validate the rating (1 to 5)
        if (bookReview.getRating() < 1 || bookReview.getRating() > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rating must be between 1 and 5.");
        }

        // Set the rating date to the current date
        bookReview.setRatingDate(LocalDate.now());

        // Call the service to update the rating and rating date in the database
        bookReviewService.updateRating(bookReview.getUserId(), bookReview.getIsbn(), bookReview.getRating(), bookReview.getRatingDate());

        return ResponseEntity.ok("Rating updated successfully.");
    }


    @PostMapping("/postComment")
    public ResponseEntity<String> postComment(@RequestBody BookReview bookReview) {

        // Validate if the purchase status is true
        BookReview existingReview = bookReviewRepo.findByUserIdAndIsbn(bookReview.getUserId(), bookReview.getIsbn());

        if (existingReview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book review not found.");
        }

        if (!existingReview.isPurchaseStatus()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You must purchase the book before commenting.");
        }

        // If the user has purchased the book, allow them to comment
        bookReview.setCommentDate(LocalDate.now());  // Set the comment date

        // Update the comment in the database
        bookReviewService.updateComment(bookReview.getUserId(), bookReview.getIsbn(), bookReview.getComment(), bookReview.getCommentDate());

        return ResponseEntity.ok("Comment posted successfully.");
    }

    @GetMapping("/getComments/{isbn}")
    public ResponseEntity<List<String>> getComments(@PathVariable String isbn) {
        // Fetch all comments for the given book ISBN
        List<String> comments = bookReviewService.getCommentsForBook(isbn);

        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // No comments found for the book
        }

        return ResponseEntity.ok(comments); // Return list of comments
    }

    @GetMapping("/getAverageRating/{isbn}")
    public ResponseEntity<Float> getAverageRating(@PathVariable String isbn) {
        // Call the service to get the average rating
        Float averageRating = bookReviewService.calculateAverageRating(isbn);

        // If no ratings found, return 0.0
        if (averageRating == null) {
            return ResponseEntity.status(404).body(0.0f);  // Not found
        }

        return ResponseEntity.ok(averageRating);
    }

}

