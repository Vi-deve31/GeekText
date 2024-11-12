package group7.geektext.controller;

import group7.geektext.model.CreditCard;
import group7.geektext.model.User;
import group7.geektext.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    // Retrieve a user by username
    @GetMapping("/{username}")
    public Optional<User> getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // Update user fields (PUT or PATCH)
    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String username, @RequestBody User user) {
        userService.updateUser(username, user);
    }

    // Add a credit card to the user
    @PostMapping("/{username}/credit-cards")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCreditCard(@PathVariable String username, @RequestBody CreditCard creditCard) {
        userService.addCreditCardToUser(username, creditCard);
    }
}
