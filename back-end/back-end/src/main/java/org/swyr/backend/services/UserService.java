package org.swyr.backend.services;

import org.swyr.backend.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.UserRepository;

import java.util.regex.Pattern;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String checkPassword(User userLogin) {
        User user = userRepository.findById(userLogin.getEmail()).orElse(null);

        if (user != null) {
            if (user.getPassword().equals(userLogin.getPassword())) {
                return user.getEmail();
            } else  {
                throw new IllegalArgumentException("Incorrect password");
            }
        }else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        return user;
    }


    public String registration(User user) {
        if(user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Username and/or password cannot be empty");
        }

        if(!checkEmail(user.getEmail())) {
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
     * Checks if the given username is a valid email.
     *
     * @param username the string to validate
     * @return true if username matches email format, false otherwise
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
