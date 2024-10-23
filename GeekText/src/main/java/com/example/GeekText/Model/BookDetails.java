package com.example.GeekText.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookDetails {
    @Id
    private Integer id;
    private Long isbn;
    private String name;
    private String description;
    private Integer price;
    private String author;
    private String genre;
    private String publisher;
    private Integer yearPublished;
    private Long copiesSold;

}
