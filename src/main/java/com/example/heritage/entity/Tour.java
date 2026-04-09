package com.example.heritage.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placeName;
    private String details;
    private String guideName;

    public Tour() {}

    public Tour(Long id, String placeName, String details, String guideName) {
        this.id = id;
        this.placeName = placeName;
        this.details = details;
        this.guideName = guideName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaceName() { return placeName; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getGuideName() { return guideName; }
    public void setGuideName(String guideName) { this.guideName = guideName; }
}