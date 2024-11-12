package group7.geektext.service;

import group7.geektext.model.CreditCard;
import group7.geektext.model.User;
import group7.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public void createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        userRepository.save(user);
    }

    // Retrieve a user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Update a user (excluding email)
    public void updateUser(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(updatedUser.getName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setHomeAddress(updatedUser.getHomeAddress());
        userRepository.save(existingUser);
    }

    // Add a credit card to a user
    public void addCreditCardToUser(String username, CreditCard creditCard) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getCreditCards().add(creditCard);
        userRepository.save(user);
    }
}
