package controllers;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseCookie login(@RequestParam String username, @RequestParam String password) {

        return "redirect:/";

    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {

        return "redirect:/";
    }

}
