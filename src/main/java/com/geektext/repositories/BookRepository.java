package com.geektext.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.geektext.models.Book;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByIsbn(String isbn);
}
