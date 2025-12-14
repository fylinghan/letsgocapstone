package services;

import entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import repos.UserRepository;

import java.util.regex.Pattern;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkPassword(String email, String password) {
        User user = userRepository.findById(email).orElse(null);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            } else  {
                throw new BadCredentialsException("Incorrect password");
            }
        }else {
            throw new EntityNotFoundException("User not found.");
        }
    }


    public boolean registration(String username, String password) {
        if(username.trim().isEmpty() || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username and/or password cannot be empty");
        }

        if(!checkEmail(username)){
            throw new IllegalArgumentException("Username is not a valid email address");
        }

        if (userRepository.existsById(username)) {
            throw new IllegalStateException("User already exists with email: " + username);
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        userRepository.save(user);
        return true;
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
