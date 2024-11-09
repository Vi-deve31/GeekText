package com.geektext.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import lombok.Data;

@Data
@Document(collection = "wishlists")
public class Wishlist {
    @Id
    private String id;
    private String name;
    private String userId;
    private List<String> bookIds;
}
