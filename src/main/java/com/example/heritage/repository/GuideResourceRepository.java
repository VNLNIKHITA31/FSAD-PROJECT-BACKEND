package com.example.heritage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.heritage.entity.GuideResource;

public interface GuideResourceRepository extends JpaRepository<GuideResource, Long> {
}