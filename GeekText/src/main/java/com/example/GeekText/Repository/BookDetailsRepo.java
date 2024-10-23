package com.example.GeekText.Repository;

import com.example.GeekText.Model.BookDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookDetailsRepo extends MongoRepository<BookDetails, Long> {
    Optional<BookDetails> findByIsbn(Long isbn);
}
