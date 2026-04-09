package com.example.heritage.entity;

import jakarta.persistence.*;

@Entity
@Table(name="guide_insight")
public class GuideInsight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String monument;
    private String content;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMonument() { return monument; }
    public void setMonument(String monument) { this.monument = monument; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}