package com.example.heritage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.heritage.entity.Discussion;
import com.example.heritage.repository.DiscussionRepository;

@RestController
@RequestMapping("/api/discussions")
@CrossOrigin
public class DiscussionController {

    @Autowired
    private DiscussionRepository repo;

    // ✅ GET ALL
    @GetMapping
    public List<Discussion> getAll() {
        return repo.findAll();
    }

    // ✅ ADD
    @PostMapping
    public Discussion addDiscussion(@RequestBody Discussion d) {
        return repo.save(d);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void deleteDiscussion(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // ✅ UPDATE (IMPORTANT)
    @PutMapping("/{id}")
    public Discussion updateDiscussion(@PathVariable Long id, @RequestBody Discussion d) {

        Discussion existing = repo.findById(id).orElseThrow();

        existing.setTitle(d.getTitle());
        existing.setMessage(d.getMessage());
        existing.setResolved(d.isResolved());
        existing.setCategory(d.getCategory());
        existing.setSite(d.getSite());

        return repo.save(existing);
    }
}