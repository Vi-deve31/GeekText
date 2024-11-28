package com.example.GeekText.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "wishlists")
public class Wishlist {
    @Id
    private String id;
    private String name;
    private String userId;
    private List<String> bookIds = new ArrayList<>();
}

