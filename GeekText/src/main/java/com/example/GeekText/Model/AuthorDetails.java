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

public class AuthorDetails {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String biography;
    private String publisher;

}


