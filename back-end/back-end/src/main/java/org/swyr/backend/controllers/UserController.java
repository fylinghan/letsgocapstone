package org.swyr.backend.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyr.backend.entities.User;
import org.swyr.backend.services.UserService;

/**
 * REST controller that manages user-related operations.
 * <p>
 * Provides endpoints for retrieving user details, handling login authentication,
 * and registering new users.
 * </p>
 *
 * Base URL: /user
 *
 * Cross-origin requests are allowed from http://localhost:5173
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a new {@code UserController} with the given service.
     *
     * @param userService the service used to handle user operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * Endpoint: GET /user/{id}
     *
     * @param id the unique identifier of the user
     * @return a {@link ResponseEntity} containing the {@link User} object
     *         and HTTP status 200 (OK), or 404 (Not Found) if the user does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Authenticates a user by verifying their login credentials.
     *
     * Endpoint: POST /user/login
     *
     * @param user the {@link User} object containing login credentials
     * @return a {@link ResponseEntity} containing the authenticated {@link User}
     *         and HTTP status 200 (OK), or 400 (Bad Request) if credentials are invalid
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.checkPassword(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Registers a new user in the system.
     *
     * Endpoint: POST /user/register
     *
     * @param user the {@link User} object containing registration details
     * @return a {@link ResponseEntity} containing the registered {@link User}
     *         and HTTP status 200 (OK), or 400 (Bad Request) if registration fails
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.registration(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}