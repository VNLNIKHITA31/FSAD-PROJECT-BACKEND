package com.example.heritage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.heritage.entity.GuideInsight;

public interface GuideInsightRepository extends JpaRepository<GuideInsight, Long> {
}