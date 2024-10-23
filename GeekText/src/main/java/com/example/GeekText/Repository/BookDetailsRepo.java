package com.example.GeekText.Repository;

import com.example.GeekText.Model.BookDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookDetailsRepo extends MongoRepository<BookDetails, Integer> {

}
