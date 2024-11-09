package com.geektext.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.geektext.models.Wishlist;
import java.util.List;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    List<Wishlist> findByUserId(String userId);
}
