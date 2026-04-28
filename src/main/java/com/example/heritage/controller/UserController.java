package com.example.heritage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.example.heritage.entity.User;
import com.example.heritage.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userRepo.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error registering user");
        }
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User u) {

        try {

            if (u.getEmail() == null || u.getPassword() == null) {
                return ResponseEntity.badRequest().body("Email or password missing");
            }

            List<User> users = userRepo.findByEmail(u.getEmail());

            if (users.isEmpty()) {
                return ResponseEntity.status(404).body("User not found");
            }

            User dbUser = users.get(0); // get user

            if (!dbUser.getPassword().equals(u.getPassword())) {
                return ResponseEntity.status(401).body("Invalid password");
            }

            return ResponseEntity.ok(dbUser);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error");
        }
    }

    // ================= RESET PASSWORD =================
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody User u) {

        try {

            if (u.getEmail() == null || u.getPassword() == null) {
                return ResponseEntity.badRequest().body("Email or password missing");
            }

            List<User> users = userRepo.findByEmail(u.getEmail());

            if (users.isEmpty()) {
                return ResponseEntity.status(404).body("User not found");
            }

            for (User user : users) {
                user.setPassword(u.getPassword());
                userRepo.save(user);
            }

            return ResponseEntity.ok("Password updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error");
        }
    }

    // ================= GET ALL USERS =================
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // ================= GET USER BY EMAIL + ROLE =================
    @GetMapping("/users/{email}/{role}")
    public ResponseEntity<?> getUserByEmailAndRole(
            @PathVariable String email,
            @PathVariable String role) {

        try {

            List<User> users = userRepo.findByEmail(email);

            if (users.isEmpty()) {
                return ResponseEntity.status(404).body("User not found");
            }

            for (User user : users) {
                if (user.getRole().equalsIgnoreCase(role)) {
                    return ResponseEntity.ok(user);
                }
            }

            return ResponseEntity.status(404).body("Role not found");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error");
        }
    }
}