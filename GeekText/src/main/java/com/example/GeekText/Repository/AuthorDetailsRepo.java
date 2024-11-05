package com.example.GeekText.Repository;

import com.example.GeekText.Model.AuthorDetails;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AuthorDetailsRepo extends MongoRepository<AuthorDetails, Integer> {

}


