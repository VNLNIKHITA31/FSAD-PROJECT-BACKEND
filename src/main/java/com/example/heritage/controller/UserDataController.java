package com.example.heritage.controller;

import com.example.heritage.entity.UserData;
import com.example.heritage.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@RestController
@RequestMapping("/api/userdata")
@CrossOrigin(origins = "*")
public class UserDataController {

    @Autowired
    private UserDataService service;

    // GET
    @GetMapping("/{email}")
    public java.util.List<UserData> getByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

    // SAVE FAVORITE (IMAGE)
    @PostMapping("/save")
    public UserData save(
            @RequestParam("email") String email,
            @RequestParam("favoritePlace") String place,
            @RequestParam("image") MultipartFile file
    ) throws Exception {

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        Path path = Paths.get(uploadDir + fileName);
        Files.write(path, file.getBytes());

        UserData data = new UserData();
        data.setEmail(email);
        data.setFavoritePlace(place);
        data.setFavoriteImage("http://localhost:8080/uploads/" + fileName);

        return service.save(data);
    }

    // 🔥 SAVE WISHLIST (NEW ROW)
    @PostMapping("/wishlist")
    public UserData saveWishlist(@RequestBody UserData data) {
        return service.save(data);
    }

    // 🔥 DELETE WISHLIST
    @DeleteMapping("/wishlist/{id}")
    public String deleteWishlist(@PathVariable Long id) {
        service.deleteWishlist(id);
        return "Deleted successfully";
    }

    // UPDATE FAVORITE
    @PutMapping("/update/{id}")
    public UserData update(
            @PathVariable Long id,
            @RequestParam("email") String email,
            @RequestParam("favoritePlace") String place,
            @RequestParam(value = "image", required = false) MultipartFile file
    ) throws Exception {

        return service.updateWithImage(id, email, place, file);
    }

    // DELETE FAVORITE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        service.deleteWithImage(id);
        return "Deleted successfully";
    }
    
 // ✅ SAVE JOURNEY (NEW)
    @PostMapping("/journey")
    public UserData saveJourney(@RequestBody UserData data) {
        return service.save(data);
    }
}