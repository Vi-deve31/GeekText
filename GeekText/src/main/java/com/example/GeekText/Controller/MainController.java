package com.example.GeekText.Controller;

/*
Specify URI (postman operations)
Controller / Service combined

 */

import com.example.GeekText.Model.BookReview;
import com.example.GeekText.Repository.BookReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class MainController {

    // POST

    @Autowired
    BookReviewRepo bookReviewRepo; // we need repo to save data. we have autowired as we are creating object in aother file

    @PostMapping("/postRating")
    public void postRating(@RequestBody BookReview bookReview) { // we are using student, as it 3 variables and our json will have 3 variables
        bookReviewRepo.save(bookReview);

        if (bookReview.getRating() < 1 || bookReview.getRating() > 5) {
            bookReview.setRating(null);
        } else {
           bookReview.setRatingDate(LocalDate.now());
        }
        bookReviewRepo.save(bookReview);
    }

    @PostMapping("/postComments")
    public void postComments(@RequestBody BookReview bookReview) { // we are using student, as it 3 variables and our json will have 3 variables
        bookReview.setCommentDate(LocalDate.now());
        bookReviewRepo.save(bookReview);
    }


    // GET

    /*

    @GetMapping("/getStudent/{userId}")
    public BookReview getStudent(@PathVariable Integer userId){
        return bookReviewRepo.findById(userId).orElse(null);
    }



     */


}
