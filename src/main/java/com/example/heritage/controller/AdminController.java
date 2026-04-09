package com.example.heritage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.heritage.entity.User;
import com.example.heritage.entity.Content;
import com.example.heritage.entity.Discussion;
import com.example.heritage.repository.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ContentRepository contentRepo;

    @Autowired
    private DiscussionRepository discussionRepo;

    // ================= STATS =================
    @GetMapping("/stats")
    public Stats getStats() {
        return new Stats(
                userRepo.count(),
                contentRepo.count(),
                discussionRepo.count()
        );
    }

    // ================= USERS =================
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

    // ================= RESPONSE CLASS =================
    static class Stats {
        public long users;
        public long contents;
        public long discussions;

        public Stats(long u, long c, long d) {
            this.users = u;
            this.contents = c;
            this.discussions = d;
        }
    }
}