package com.example.GeekText.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.GeekText.model.ShoppingCart;


public interface ShoppingCartRepo extends MongoRepository<ShoppingCart, String> {
    List<ShoppingCart> findByUserId(String userId);
}