package com.example.GeekText.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.GeekText.Model.Book;
import com.example.GeekText.model.ShoppingCart;
import com.example.GeekText.Repository.ShoppingCartRepo;

@Service
public class CartService {

    @Autowired
    private ShoppingCartRepo cartRepo; //repo for cart operations

    @Autowired
    private MongoTemplate mongoTemplate; //for custom mongo queries


    //finds the carts for each user
    public double getSubtotal(String userId) {
        List<ShoppingCart> userCarts = cartRepo.findByUserId(userId);
        if (userCarts.isEmpty()) {
            return 0.0;
        }
        ShoppingCart cart = userCarts.get(0);
        return cart.getBooks().stream()
        .mapToDouble(book -> book.getPrice())
        .sum(); //java streams to sum up all book prices in the cart
    }

    //retrieves all books in the user cart
        public List<ShoppingCart.BooksInCart> getBooks(String userId) {
            List<ShoppingCart> userCarts = cartRepo.findByUserId(userId);
            if (userCarts.isEmpty()) {
                return new ArrayList<>();
            }
            return userCarts.get(0).getBooks(); //empty list if no cart exists
        }

    //finds the book in the db 
        public void addBook(String userId, String bookId) {
            Query query = new Query();
            query.addCriteria(Criteria.where("bookId").is(bookId));
            Book book = mongoTemplate.findOne(query, Book.class);

            if (book == null) {
                throw new RuntimeException("Book not found with ID: " + userId);  
            }
    //create or get the cart for the user
            List<ShoppingCart> userCarts = cartRepo.findByUserId(userId);
            ShoppingCart cart;
    //create new cart if none exists        
            if (userCarts.isEmpty()) {
                cart = new ShoppingCart();
                cart.setUserId(userId);
                cart.setShoppingCartId("Cart-" + userId);
                cart.setBooks(new ArrayList<>());
                cart.setCreatedAt(Instant.now().toString());
            } else {
                cart = userCarts.get(0);
            }

            //new book entry 
            ShoppingCart.BooksInCart cartBook = new ShoppingCart.BooksInCart();
            cartBook.setBookId(book.getBookId());
            cartBook.setTitle(book.getTitle());
            cartBook.setAuthor(book.getAuthor());
            cartBook.setPrice(book.getPrice());

            //add and update cart
            cart.getBooks().add(cartBook);
            cart.setUpdatedAt(Instant.now().toString());

            //save
            cartRepo.save(cart);
        }

        //remove book
        public void removeBook(String userId, String bookId) {
            List<ShoppingCart> userCarts = cartRepo.findByUserId(userId);
            if(userCarts.isEmpty()) {
                throw new RuntimeException("Cart not found for user: " + userId);
            }
            
            ShoppingCart cart = userCarts.get(0);
            cart.getBooks().removeIf(book -> book.getBookId().equals(bookId));
            cart.setUpdatedAt(Instant.now().toString());
            cartRepo.save(cart);
        }

    }