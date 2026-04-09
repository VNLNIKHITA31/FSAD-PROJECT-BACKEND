package com.example.heritage.controller;

import com.example.heritage.entity.Content;
import com.example.heritage.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.nio.file.*;
import java.io.IOException;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins = "http://localhost:5173")
public class ContentController {

    @Autowired
    private ContentService service;

    @GetMapping
    public List<Content> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Content add(@RequestBody Content content) {

        System.out.println("===== RECEIVED DATA =====");
        System.out.println(content);

        if (content.getTitle() == null) content.setTitle("");
        if (content.getState() == null) content.setState("");
        if (content.getCategory() == null) content.setCategory("");
        if (content.getType() == null) content.setType("");
        if (content.getDescription() == null) content.setDescription("");
        if (content.getImage() == null) content.setImage("");

        if (content.getRating() == null) content.setRating(0);

        return service.save(content);
    }

    @PutMapping("/{id}")
    public Content update(@PathVariable Long id, @RequestBody Content content) {

        content.setId(id);

        if (content.getTitle() == null) content.setTitle("");
        if (content.getState() == null) content.setState("");
        if (content.getCategory() == null) content.setCategory("");
        if (content.getType() == null) content.setType("");
        if (content.getDescription() == null) content.setDescription("");
        if (content.getImage() == null) content.setImage("");

        if (content.getRating() == null) content.setRating(0);

        return service.save(content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            Files.write(Paths.get(filePath), file.getBytes());

            return "http://localhost:8080/uploads/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file";
        }
    }
}