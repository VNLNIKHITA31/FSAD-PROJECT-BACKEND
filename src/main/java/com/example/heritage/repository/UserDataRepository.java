package com.example.heritage.repository;

import com.example.heritage.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    List<UserData> findByEmail(String email);
}