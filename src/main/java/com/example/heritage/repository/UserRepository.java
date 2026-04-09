package com.example.heritage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.heritage.entity.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

}