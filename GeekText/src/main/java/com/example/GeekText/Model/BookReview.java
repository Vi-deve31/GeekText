package com.example.GeekText.Model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document // indication to springboot that this will be our table name/document name in mongo compass
@Data // getters, setters
@NoArgsConstructor
@AllArgsConstructor
public class BookReview { // model class has our keys in the mongodb

    @Id // our id is our primary key, unique
    private Integer userId;
    private String isbn;
    private Integer rating;
    private LocalDate ratingDate;
    private String comment;
    private LocalDate commentDate;
    private boolean purchaseStatus;
    private Float averageRating;

    public boolean isPurchaseStatus() {
        return purchaseStatus;
    }
}
