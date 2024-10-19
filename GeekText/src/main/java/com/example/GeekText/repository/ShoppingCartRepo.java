package com.example.GeekText.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.GeekText.model.ShoppingCart;



public interface ShoppingCartRepo extends MongoRepository<ShoppingCart, String> {

    Optional<ShoppingCart> findByUserId(String userId);
}
