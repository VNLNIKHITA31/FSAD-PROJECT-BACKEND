package com.example.heritage.service;

import com.example.heritage.entity.Discussion;
import com.example.heritage.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository repo;

    public List<Discussion> getAll() {
        return repo.findAll();
    }

    public Discussion save(Discussion discussion) {
        return repo.save(discussion);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}