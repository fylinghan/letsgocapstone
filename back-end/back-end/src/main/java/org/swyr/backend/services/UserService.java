package org.swyr.backend.services;

import org.swyr.backend.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.UserRepository;

import java.util.regex.Pattern;

/**
 * Service class that provides user-related operations such as authentication,
 * registration, and retrieval. This class acts as a bridge between the
 * {@link UserRepository} and higher-level application logic.
 *
 * <p>Responsibilities include:</p>
 * <ul>
 *   <li>Validating user credentials during login</li>
 *   <li>Registering new users with proper validation</li>
 *   <li>Fetching user details by ID (email)</li>
 *   <li>Ensuring email format validity</li>
 * </ul>
 *
 * <p>Exceptions are thrown for invalid input, missing users, or duplicate registrations
 * to enforce data integrity and security.</p>
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@code UserService} with the given repository.
     *
     * @param userRepository the repository used for user persistence and retrieval
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Validates a user's login credentials by checking the provided password
     * against the stored password for the given email.
     *
     * @param userLogin the user object containing login credentials (email and password)
     * @return the email of the authenticated user if credentials are valid
     * @throws IllegalArgumentException if the password is incorrect
     * @throws EntityNotFoundException if no user exists with the given email
     */
    public String checkPassword(User userLogin) {
        User user = userRepository.findById(userLogin.getEmail()).orElse(null);

        if (user != null) {
            if (user.getPassword().equals(userLogin.getPassword())) {
                return user.getEmail();
            } else {
                throw new IllegalArgumentException("Incorrect password");
            }
        } else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    /**
     * Retrieves a user by their unique identifier (email).
     *
     * @param id the email of the user to retrieve
     * @return the {@link User} object associated with the given email
     * @throws IllegalArgumentException if no user exists with the given email
     */
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        return user;
    }

    /**
     * Registers a new user in the system after validating input fields and ensuring
     * uniqueness of the email.
     *
     * <p>Validation includes:</p>
     * <ul>
     *   <li>Email and password must not be empty</li>
     *   <li>Email must follow a valid format</li>
     *   <li>Email must not already exist in the repository</li>
     * </ul>
     *
     * @param user the user object containing registration details
     * @return a confirmation message indicating successful registration
     * @throws IllegalArgumentException if email or password is empty, or email is invalid
     * @throws IllegalStateException if a user already exists with the given email
     */
    public String registration(User user) {
        if (user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Username and/or password cannot be empty");
        }

        if (!checkEmail(user.getEmail())) {
            throw new IllegalArgumentException("Username is not a valid email address");
        }

        if (userRepository.existsById(user.getEmail())) {
            throw new IllegalStateException("User already exists with email: " + user.getEmail());
        }

        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return user.getEmail() + " is now registered. Please log in.";
    }

    /**
     * Checks if the given username is a valid email address.
     *
     * @param username the string to validate
     * @return {@code true} if the username matches email format, {@code false} otherwise
     */
    public static boolean checkEmail(String username) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern EMAIL_PATTERN = Pattern.compile(emailRegex);

        if (username == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(username.trim()).matches();
    }
}