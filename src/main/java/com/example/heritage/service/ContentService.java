package com.example.heritage.service;

import com.example.heritage.entity.Content;
import com.example.heritage.repository.ContentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository repo;

    public List<Content> getAll() {
        return repo.findAll();
    }

    public Content save(Content content) {
        return repo.save(content);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}