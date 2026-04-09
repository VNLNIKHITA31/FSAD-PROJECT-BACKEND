package com.example.heritage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.heritage.entity.GuideSession;

public interface GuideSessionRepository extends JpaRepository<GuideSession, Long> {
}