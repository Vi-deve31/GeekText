package com.geektext.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.geektext.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
