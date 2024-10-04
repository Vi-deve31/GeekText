package com.example.GeekText.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.GeekText.model.ShoppingCart;


@Repository
public interface ShoppingCartRepo extends MongoRepository<ShoppingCart, String> {
    
    //find a shopping cart by the users ID
    Optional<ShoppingCart> findByUserId(String userId);

    //delete a shopping cart by users ID
    void deledeleteByUserId(String userId);

    //checks if cart exists for specific userID
    boolean existexistsByUserId(String userId);

    //counts number of items in users cart
    long countcountByUserId(String userId);
}