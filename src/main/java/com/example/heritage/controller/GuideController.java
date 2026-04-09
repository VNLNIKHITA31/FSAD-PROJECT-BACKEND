package com.example.heritage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.nio.file.*;

import com.example.heritage.entity.*;
import com.example.heritage.repository.*;

@RestController
@RequestMapping("/api/guide")
@CrossOrigin(origins = "*")
public class GuideController {

    @Autowired
    private DiscussionRepository discussionRepo;

    @Autowired
    private GuideInsightRepository insightRepo;

    @Autowired
    private GuideSessionRepository sessionRepo;

    @Autowired
    private GuideResourceRepository resourceRepo;

    @Autowired
    private GuideProfileRepository profileRepo;

    // ================= QUESTIONS =================
    @GetMapping("/questions")
    public List<Discussion> getQuestions() {
        return discussionRepo.findAll();
    }

    @PutMapping("/reply/{id}")
    public Discussion reply(@PathVariable Long id, @RequestBody Discussion d) {
        d.setId(id);
        return discussionRepo.save(d);
    }

    // ================= INSIGHTS =================
    @GetMapping("/insights")
    public List<GuideInsight> getInsights() {
        return insightRepo.findAll();
    }

    @PostMapping("/insights")
    public GuideInsight addInsight(@RequestBody GuideInsight i) {
        return insightRepo.save(i);
    }

    // ================= SESSIONS =================
    @GetMapping("/sessions")
    public List<GuideSession> getSessions() {
        return sessionRepo.findAll();
    }

    @PostMapping("/sessions")
    public GuideSession addSession(@RequestBody GuideSession s) {
        return sessionRepo.save(s);
    }

    // ================= RESOURCES (OLD - JSON) =================
    @GetMapping("/resources")
    public List<GuideResource> getResources() {
        return resourceRepo.findAll();
    }

    @PostMapping("/resources")
    public GuideResource addResource(@RequestBody GuideResource r) {
        return resourceRepo.save(r);
    }

    // ================= RESOURCES (NEW - FILE UPLOAD) =================
    @PostMapping("/upload-resource")
    public GuideResource uploadResource(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        // Create uploads folder
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        // Save file
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);
        Files.write(path, file.getBytes());

        // Save to DB
        GuideResource resource = new GuideResource();
        resource.setTitle(title);
        resource.setDescription(description);
        resource.setLink("http://localhost:8080/uploads/" + fileName);

        return resourceRepo.save(resource);
    }

    // ================= PROFILE =================
    @GetMapping("/profile")
    public List<GuideProfile> getProfile() {
        return profileRepo.findAll();
    }

    @PostMapping("/profile")
    public GuideProfile saveProfile(@RequestBody GuideProfile p) {
        return profileRepo.save(p);
    }
}