package com.example.heritage.service;

import com.example.heritage.entity.UserData;
import com.example.heritage.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.List;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository repo;

    // ✅ GET BY EMAIL
    public List<UserData> getByEmail(String email) {
        return repo.findByEmail(email);
    }

    // 🔥 ✅ SAVE (ALWAYS NEW ROW)
    public UserData save(UserData data) {
        return repo.save(data);
    }

    // ✅ GET BY ID
    public UserData getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    // ✅ UPDATE WITH IMAGE (FAVORITES)
    public UserData updateWithImage(Long id, String email, String place, MultipartFile file) throws Exception {

        UserData existing = repo.findById(id).orElseThrow();

        existing.setEmail(email);
        existing.setFavoritePlace(place);

        if (file != null && !file.isEmpty()) {

            if (existing.getFavoriteImage() != null) {
                String oldPath = existing.getFavoriteImage().replace("http://localhost:8080/", "");
                Files.deleteIfExists(Paths.get(oldPath));
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String uploadDir = "uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, file.getBytes());

            existing.setFavoriteImage("http://localhost:8080/uploads/" + fileName);
        }

        return repo.save(existing);
    }

    // ✅ DELETE WITH IMAGE DELETE
    public void deleteWithImage(Long id) throws Exception {

        UserData existing = repo.findById(id).orElseThrow();

        if (existing.getFavoriteImage() != null) {
            String filePath = existing.getFavoriteImage().replace("http://localhost:8080/", "");
            Files.deleteIfExists(Paths.get(filePath));
        }

        repo.deleteById(id);
    }

    // 🔥 DELETE WISHLIST
    public void deleteWishlist(Long id) {
        repo.deleteById(id);
    }
}